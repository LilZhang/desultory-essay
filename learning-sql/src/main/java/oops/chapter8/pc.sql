SELECT
	CONCAT(p1.BrandName, "(", p1.BrandId,")") as brand,
	CONCAT(p1.`Name`, "(",p1.Id,")") as `name_1`,
	pc2.`name` as name_2,
	pc2.name_3 as name_3,
	pc2.name_4 as name_4,
	pc2.name_5 as name_5,
	pc2.name_6 as name_6,
	pc2.name_7 as name_7,
	pc2.name_8 as name_8
FROM
	product_category p1
LEFT JOIN
(
  SELECT
    p2.Id as id,
    CONCAT(p2.`Name`, "(",p2.Id,")") as `name`,
    p2.ParentId as parentId,
    pc3.name as name_3,
    pc3.name_4 as name_4,
    pc3.name_5 as name_5,
    pc3.name_6 as name_6,
    pc3.name_7 as name_7,
    pc3.name_8 as name_8
  FROM
    product_category p2
  LEFT JOIN
  (
    SELECT
      p3.Id as id,
      CONCAT(p3.`Name`, "(",p3.Id,")") as `name`,
      p3.ParentId as parentId,
      pc4.name as name_4,
      pc4.name_5 as name_5,
      pc4.name_6 as name_6,
      pc4.name_7 as name_7,
      pc4.name_8 as name_8
    FROM
      product_category p3
    LEFT JOIN
    (
      SELECT
        p4.Id as id,
        CONCAT(p4.`Name`, "(",p4.Id,")") as `name`,
        p4.ParentId as parentId,
        pc5.name as name_5,
        pc5.name_6 as name_6,
        pc5.name_7 as name_7,
        pc5.name_8 as name_8
      FROM
        product_category p4
      LEFT JOIN
      (
        SELECT
          p5.Id as id,
          CONCAT(p5.`Name`, "(",p5.Id,")") as `name`,
          p5.ParentId as parentId,
          pc6.name as name_6,
          pc6.name_7 as name_7,
          pc6.name_8 as name_8
        FROM
          product_category p5
        LEFT JOIN
        (
          SELECT
            p6.Id as id,
            CONCAT(p6.`Name`, "(",p6.Id,")") as `name`,
            p6.ParentId as parentId,
            pc7.name as name_7,
            pc7.name_8 as name_8
          FROM
            product_category p6
          LEFT JOIN
          (
            SELECT
              p7.Id as id,
              CONCAT(p7.`Name`, "(",p7.Id,")") as `name`,
              p7.ParentId as parentId,
              pc8.name as name_8
            FROM
              product_category p7
            LEFT JOIN
            (
              SELECT
                p8.Id as id,
                CONCAT(p8.`Name`, "(",p8.Id,")") as `name`,
                p8.ParentId as parentId
              FROM
                product_category p8
              WHERE
                p8.`Status` = 1
              AND p8.`Level` = 8
            ) pc8 ON pc8.parentId = p7.id
            WHERE
              p7.`Status` = 1
            AND p7.`Level` = 7
          ) pc7 ON pc7.parentId = p6.id
          WHERE
            p6.`Status` = 1
          AND p6.`Level` = 6
        ) pc6 ON pc6.parentId = p5.id
        WHERE
          p5.`Status` = 1
        AND p5.`Level` = 5
      ) pc5 ON pc5.parentId = p4.id
      WHERE
        p4.`Status` = 1
      AND p4.`Level` = 4
    ) pc4 ON pc4.parentId = p3.id
    WHERE
      p3.`Status` = 1
    AND p3.`Level` = 3
  ) pc3 ON pc3.parentId = p2.id
  WHERE
    p2.`Status` = 1
  AND p2.`Level` = 2
) pc2 ON pc2.parentId = p1.id
WHERE
	p1.`Status` = 1
AND p1.`Level` = 1;