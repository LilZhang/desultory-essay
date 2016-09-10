### 子查询作为表达式生成器

```
SELECT
    ua.Id as user_id,
    (SELECT ue.`Name` FROM user_enterprise ue WHERE ue.UserId = ua.Id) as user_name,
    ua.SaleName as sale_name,
    (SELECT CASE ue.Type
    WHEN 0 then "未认证"
   WHEN 1 then "品牌授权分销商"
   WHEN 2 THEN "普通分销商"
   WHEN 3 THEN "OEM"
   WHEN 4 THEN "盘厂"
   WHEN 5 THEN "系统集成商"
   WHEN 6 THEN "最终用户"
   WHEN 7 THEN "非目标用户"
        END  FROM user_enterprise ue where ua.Id = ue.UserId ) as user_category,
    (SELECT COUNT(ull.Id) FROM user_login_log ull WHERE ull.UserId = ua.Id) as user_login_count,
    ua.AddTime as register_time,
    CASE ua.ProfileType 
    WHEN 0 THEN "未激活"
    WHEN 1 THEN "已激活"
    END as activated,
    (SELECT MAX(ull.AddTime) FROM user_login_log ull WHERE ull.UserId = ua.Id AND ull.AddTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as last_login_time,
    (SELECT COUNT(ksl.Id) FROM lingku_search.keyword_search_log ksl WHERE ksl.UserId = ua.Id AND ksl.AddTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as search_count_this_week,
    (SELECT COUNT(fp.Id) FROM favorite_product fp WHERE fp.UserId = ua.Id AND fp.UpdateTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as browse_product_count_this_week,
    (SELECT COUNT(ual.Id) FROM user_access_log ual WHERE ual.UserId = ua.Id AND ual.Type = '/v/business/distributor/contact' AND ual.AddTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as seller_view_count,
    (SELECT COUNT(DISTINCT ual.BizId) FROM user_access_log ual WHERE ual.UserId = ua.Id AND ual.Type = '/v/business/distributor/contact' AND ual.AddTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as view_seller_count,
    (SELECT COUNT(ci.Id) FROM cart_item ci WHERE ci.UserId = ua.Id AND ci.AddTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as incoming_cart_product_this_week,
    (SELECT MAX(ci.AddTime) FROM cart_item ci WHERE ci.UserId = ua.Id AND ci.AddTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as last_incoming_cart_product_add_time,
    (SELECT MAX(po.AddTime) FROM pay_order po WHERE po.BuyerId = ua.Id AND po.AddTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as last_order_time,
    (SELECT COUNT(po.Id) FROM pay_order po WHERE po.BuyerId = ua.Id AND po.`Status` IN (6, 9, 11) AND po.UpdateTime BETWEEN '2016-08-08 00:00:00' AND '2016-08-15 23:59:59') as order_succeed_count_this_week
FROM
    user_account ua
```