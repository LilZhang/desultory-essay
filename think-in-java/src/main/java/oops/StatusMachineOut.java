/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-12
 * Project        : desultory-essay
 * File Name      : StatusMachineOut.java
 */
public class StatusMachineOut
{
    public static void main(String[] args)
    {
        Map<OrderStatusEnum, OrderState> states = new LinkedHashMap<OrderStatusEnum, OrderState>();

        OrderState state = new OrderState(OrderStatusEnum.CREATED);
        state.getBuyerEvents().put(OrderEventEnum.BUYER_CANCEL, OrderStatusEnum.BUYER_CANCELD);
        state.getBuyerEvents().put(OrderEventEnum.PAY_SUCCESS, OrderStatusEnum.PAY_SUCCESS);
        state.getBuyerEvents().put(OrderEventEnum.PAY_FAILURE, OrderStatusEnum.PAY_FAILURE);
        state.getBuyerEvents().put(OrderEventEnum.PAY_APPLY_KUPAY, OrderStatusEnum.PAY_KUPAY_APPLYING);
        state.getBuyerEvents().put(OrderEventEnum.OFFLINE_APPLY, OrderStatusEnum.OFFLINE_APPLY);
        state.getSellerEvents().put(OrderEventEnum.MODIFY, OrderStatusEnum.CREATED);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.BUYER_CANCELD);
        state.getSysEvents().put(OrderEventEnum.REFUND_ACCEPT, OrderStatusEnum.REFUND_SUCCESS);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.SELLER_CANCELD);
        state.getSysEvents().put(OrderEventEnum.REFUND_ACCEPT, OrderStatusEnum.REFUND_SUCCESS);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.PAY_FAILURE);
        state.getBuyerEvents().put(OrderEventEnum.BUYER_CANCEL, OrderStatusEnum.BUYER_CANCELD);
        state.getBuyerEvents().put(OrderEventEnum.PAY_SUCCESS, OrderStatusEnum.PAY_SUCCESS);
        state.getBuyerEvents().put(OrderEventEnum.PAY_FAILURE, OrderStatusEnum.PAY_FAILURE);
        state.getBuyerEvents().put(OrderEventEnum.PAY_APPLY_KUPAY, OrderStatusEnum.PAY_KUPAY_APPLYING);
        state.getBuyerEvents().put(OrderEventEnum.OFFLINE_APPLY, OrderStatusEnum.OFFLINE_APPLY);
        state.getSellerEvents().put(OrderEventEnum.MODIFY, OrderStatusEnum.CREATED);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.PAY_KUPAY_APPLYING);
        state.getSysEvents().put(OrderEventEnum.PAY_SUCCESS, OrderStatusEnum.PAY_SUCCESS);
        state.getSysEvents().put(OrderEventEnum.PAY_FAILURE, OrderStatusEnum.PAY_FAILURE);
        state.getSysEvents().put(OrderEventEnum.PAY_APPLY_KUPAY, OrderStatusEnum.PAY_KUPAY_APPLYING);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.PAY_KUPAY_REJECTED);
        state.getBuyerEvents().put(OrderEventEnum.BUYER_CANCEL, OrderStatusEnum.BUYER_CANCELD);
        state.getBuyerEvents().put(OrderEventEnum.PAY_SUCCESS, OrderStatusEnum.PAY_SUCCESS);
        state.getBuyerEvents().put(OrderEventEnum.PAY_FAILURE, OrderStatusEnum.PAY_FAILURE);
        state.getBuyerEvents().put(OrderEventEnum.PAY_APPLY_KUPAY, OrderStatusEnum.PAY_KUPAY_APPLYING);
        state.getBuyerEvents().put(OrderEventEnum.OFFLINE_APPLY, OrderStatusEnum.OFFLINE_APPLY);
        state.getSellerEvents().put(OrderEventEnum.MODIFY, OrderStatusEnum.CREATED);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.PAY_SUCCESS);
        state.getBuyerEvents().put(OrderEventEnum.BUYER_CANCEL, OrderStatusEnum.BUYER_CANCELD);
        state.getSellerEvents().put(OrderEventEnum.CONFIRM, OrderStatusEnum.CONFIRMED);
        state.getSellerEvents().put(OrderEventEnum.CONFIRM_TRANSFER, OrderStatusEnum.CONFIRMED);
        state.getSellerEvents().put(OrderEventEnum.SELLER_CANCEL, OrderStatusEnum.SELLER_CANCELD);
        state.getSysEvents().put(OrderEventEnum.EXPIRE, OrderStatusEnum.SELLER_CANCELD);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.CONFIRMED);
        state.getSellerEvents().put(OrderEventEnum.SHIPPING, OrderStatusEnum.SHIPPING);
        state.getSellerEvents().put(OrderEventEnum.SELLER_CANCEL, OrderStatusEnum.SELLER_CANCELD);
        state.getSysEvents().put(OrderEventEnum.EXPIRE, OrderStatusEnum.SELLER_CANCELD);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.SHIPPING);
        state.getBuyerEvents().put(OrderEventEnum.RECEIVE, OrderStatusEnum.RECEIVED);
        state.getBuyerEvents().put(OrderEventEnum.REFUND_APPLY, OrderStatusEnum.REFUND_APPLYING);
        state.getSysEvents().put(OrderEventEnum.EXPIRE, OrderStatusEnum.RECEIVED);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.REFUND_APPLYING);
        state.getBuyerEvents().put(OrderEventEnum.RECEIVE, OrderStatusEnum.RECEIVED);
        state.getBuyerEvents().put(OrderEventEnum.REFUND_APPLY_CANCEL, OrderStatusEnum.SHIPPING);
        state.getSellerEvents().put(OrderEventEnum.REFUND_ACCEPT, OrderStatusEnum.REFUND_SELLER_ACCEPT);
        state.getSellerEvents().put(OrderEventEnum.REFUND_REJECT, OrderStatusEnum.REFUND_KU_MEDIATION);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.REFUND_SELLER_ACCEPT);
        state.getSysEvents().put(OrderEventEnum.REFUND_SUCCESS, OrderStatusEnum.REFUND_SUCCESS);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.RECEIVED);
        state.getBuyerEvents().put(OrderEventEnum.BUYER_REVIEW, OrderStatusEnum.BUYER_REVIEWED);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.OFFLINE_APPLY);
        state.getSysEvents().put(OrderEventEnum.OFFLINE_CONFIRM, OrderStatusEnum.OFFLINE_SUCCESS);
        state.getSellerEvents().put(OrderEventEnum.SHIPPING, OrderStatusEnum.OFFLINE_APPLY);
        states.put(state.getCurernt(), state);

        state = new OrderState(OrderStatusEnum.OFFLINE_SUCCESS);
        state.getBuyerEvents().put(OrderEventEnum.BUYER_REVIEW, OrderStatusEnum.BUYER_REVIEWED);
        states.put(state.getCurernt(), state);

        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = new FileInputStream("E:\\Program Files (x86)\\下单流程.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(1);

            XSSFRow row;
            XSSFCell cell;

            int rowNum = 2;
            for (Map.Entry<OrderStatusEnum, OrderState> entry : states.entrySet())
            {
                OrderStatusEnum key = entry.getKey();
                Map<OrderEventEnum, OrderStatusEnum> buyerEvents = entry.getValue().getBuyerEvents();

                for (Map.Entry<OrderEventEnum, OrderStatusEnum> buyerEvent : buyerEvents.entrySet())
                {
                    row = sheet.createRow(rowNum++);

                    cell = row.createCell(0);
                    cell.setCellValue(key.getBuyerDesc());

                    cell = row.createCell(1);
                    cell.setCellValue(key.getSellerDesc());

                    cell = row.createCell(2);
                    cell.setCellValue(key.getSysDesc());

                    cell = row.createCell(3);
                    cell.setCellValue(key.getStatus());

                    cell = row.createCell(4);
                    cell.setCellValue("买家");

                    cell = row.createCell(5);
                    cell.setCellValue(buyerEvent.getKey().getMessage());

                    OrderStatusEnum value = buyerEvent.getValue();

                    cell = row.createCell(6);
                    cell.setCellValue(value.getBuyerDesc());

                    cell = row.createCell(7);
                    cell.setCellValue(value.getSellerDesc());

                    cell = row.createCell(8);
                    cell.setCellValue(value.getSysDesc());

                    cell = row.createCell(9);
                    cell.setCellValue(value.getStatus());
                }

                Map<OrderEventEnum, OrderStatusEnum> sellerEvents = entry.getValue().getSellerEvents();

                for (Map.Entry<OrderEventEnum, OrderStatusEnum> sellerEvent : sellerEvents.entrySet())
                {
                    row = sheet.createRow(rowNum++);

                    cell = row.createCell(0);
                    cell.setCellValue(key.getBuyerDesc());

                    cell = row.createCell(1);
                    cell.setCellValue(key.getSellerDesc());

                    cell = row.createCell(2);
                    cell.setCellValue(key.getSysDesc());

                    cell = row.createCell(3);
                    cell.setCellValue(key.getStatus());

                    cell = row.createCell(4);
                    cell.setCellValue("卖家");

                    cell = row.createCell(5);
                    cell.setCellValue(sellerEvent.getKey().getMessage());

                    OrderStatusEnum value = sellerEvent.getValue();

                    cell = row.createCell(6);
                    cell.setCellValue(value.getBuyerDesc());

                    cell = row.createCell(7);
                    cell.setCellValue(value.getSellerDesc());

                    cell = row.createCell(8);
                    cell.setCellValue(value.getSysDesc());

                    cell = row.createCell(9);
                    cell.setCellValue(value.getStatus());
                }

                Map<OrderEventEnum, OrderStatusEnum> sysEvents = entry.getValue().getSysEvents();

                for (Map.Entry<OrderEventEnum, OrderStatusEnum> sysEvent : sysEvents.entrySet())
                {
                    row = sheet.createRow(rowNum++);

                    cell = row.createCell(0);
                    cell.setCellValue(key.getBuyerDesc());

                    cell = row.createCell(1);
                    cell.setCellValue(key.getSellerDesc());

                    cell = row.createCell(2);
                    cell.setCellValue(key.getSysDesc());

                    cell = row.createCell(3);
                    cell.setCellValue(key.getStatus());

                    cell = row.createCell(4);
                    cell.setCellValue("后台");

                    cell = row.createCell(5);
                    cell.setCellValue(sysEvent.getKey().getMessage());

                    OrderStatusEnum value = sysEvent.getValue();

                    cell = row.createCell(6);
                    cell.setCellValue(value.getBuyerDesc());

                    cell = row.createCell(7);
                    cell.setCellValue(value.getSellerDesc());

                    cell = row.createCell(8);
                    cell.setCellValue(value.getSysDesc());

                    cell = row.createCell(9);
                    cell.setCellValue(value.getStatus());
                }
            }

            out = new FileOutputStream("E:\\Program Files (x86)\\下单流程_out.xlsx");
            workbook.write(out);
            out.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }

                if (out != null)
                {
                    out.close();
                }
            }
            catch (Exception e)
            {
                // ignore
            }
        }

    }

    private static class OrderState implements Serializable
    {
        private OrderStatusEnum curernt;

        private Map<OrderEventEnum, OrderStatusEnum> buyerEvents = new HashMap<OrderEventEnum, OrderStatusEnum>();

        private Map<OrderEventEnum, OrderStatusEnum> sellerEvents = new HashMap<OrderEventEnum, OrderStatusEnum>();

        private Map<OrderEventEnum, OrderStatusEnum> sysEvents = new HashMap<OrderEventEnum, OrderStatusEnum>();

        public OrderState(OrderStatusEnum curernt)
        {
            this.curernt = curernt;
        }

        public OrderStatusEnum getCurernt()
        {
            return curernt;
        }

        public void setCurernt(OrderStatusEnum curernt)
        {
            this.curernt = curernt;
        }

        public Map<OrderEventEnum, OrderStatusEnum> getBuyerEvents()
        {
            return buyerEvents;
        }

        public void setBuyerEvents(Map<OrderEventEnum, OrderStatusEnum> buyerEvents)
        {
            this.buyerEvents = buyerEvents;
        }

        public Map<OrderEventEnum, OrderStatusEnum> getSellerEvents()
        {
            return sellerEvents;
        }

        public void setSellerEvents(Map<OrderEventEnum, OrderStatusEnum> sellerEvents)
        {
            this.sellerEvents = sellerEvents;
        }

        public Map<OrderEventEnum, OrderStatusEnum> getSysEvents()
        {
            return sysEvents;
        }

        public void setSysEvents(Map<OrderEventEnum, OrderStatusEnum> sysEvents)
        {
            this.sysEvents = sysEvents;
        }
    }

    private enum OrderStatusEnum
    {
        CREATED(1, "主状态", "已提交订单", "买家已提交订单", "买家已提交订单", true, OrderStepEnum.CREATION),

        CONFIRMED(3, "主状态", "等待卖家发货", "已确认订单", "卖家已确认订单", true, OrderStepEnum.CONFIRM),

        SHIPPING(5, "主状态", "卖家已发货", "等待买家收货", "卖家已发货，等待买家收货", true, OrderStepEnum.SHIPPED),

        RECEIVED(6, "主状态", "交易成功", "交易成功", "买家已收货，交易成功", true, OrderStepEnum.RECEIVED),

        BUYER_CANCELD(7, "主状态", "已取消订单", "买家已取消订单", "买家已取消订单", true, OrderStepEnum.BUYER_CANCEL),

        SELLER_CANCELD(8, "主状态", "卖家已取消订单", "已取消订单", "卖家已取消订单", true, OrderStepEnum.SELLER_CANCEL),

        BUYER_REVIEWED(9, "主状态", "交易成功", "交易成功", "买家已评价", true, OrderStepEnum.REVIEWD),

        OFFLINE_APPLY(10, "主状态", "线下交易中", "买家申请线下交易", "买家申请线下交易", true, OrderStepEnum.OFFLINE_AAPLY),

        OFFLINE_SUCCESS(11, "主状态", "线下交易成功", "线下交易成功", "线下交易成功", true, OrderStepEnum.OFFLINE_CONFIRM),

        PAY_SUCCESS(200, "主状态", "已付款", "买家已付款", "买家已付款", true, OrderStepEnum.PAY),

        PAY_UNKOWN_ERROR(201, "支付", "系统确认中", "等待买家付款", "支付系统错误", false, OrderStepEnum.CREATION),

        PAY_FAILURE(202, "支付", "等待付款", "等待买家付款", "支付失败，等待买家付款", false, OrderStepEnum.CREATION),

        PAY_KUPAY_APPLYING(203, "支付", "零库担保审核中", "等待买家付款", "零库担保审核中", false, OrderStepEnum.CREATION),

        PAY_KUPAY_REJECTED(204, "支付", "零库担保审核不通过", "等待买家付款", "零库担保审核不通过", false, OrderStepEnum.CREATION),

        REFUND_SUCCESS(400, "主状态", "已完成退款", "交易关闭", "已完成退款", true, OrderStepEnum.REFUND_SUCCESS),

        REFUND_APPLYING(401, "退款", "退款申请中", "买家申请退款", "买家申请退款", true, OrderStepEnum.REFUNDING),

        REFUND_KU_MEDIATION(402, "退款", "零库调解中", "零库调解中", "零库调解中", false, OrderStepEnum.REFUNDING),

        REFUND_SELLER_ACCEPT(403, "退款", "等待系统退款", "交易关闭", "卖家同意退款，等待系统退款", false, OrderStepEnum.REFUNDING),

        SYSTEM_CLOSE(12, "订单关闭", "订单关闭", "订单关闭", "系统关闭订单", true, OrderStepEnum.SYSTEM_CLOSE);

        private int status;

        private String buyerDesc;

        private String sellerDesc;

        private String sysDesc;

        private String type;

        private boolean major;

        private OrderStepEnum step;

        OrderStatusEnum(int status, String type, String buyerDesc, String sellerDesc, boolean major)
        {
            this(status, type, buyerDesc, sellerDesc, "", major, null);
        }

        OrderStatusEnum(int status, String type, String buyerDesc, String sellerDesc, String sysDesc, boolean major, OrderStepEnum step)
        {
            this.status = status;
            this.buyerDesc = buyerDesc;
            this.sellerDesc = sellerDesc;
            this.sysDesc = sysDesc;
            this.type = type;
            this.major = major;
            this.step = step;
        }

        public static OrderStatusEnum get(int status)
        {
            OrderStatusEnum[] values = OrderStatusEnum.values();
            for (OrderStatusEnum FreightRuleTypeEnum : values)
            {
                if (FreightRuleTypeEnum.getStatus() == status)
                {
                    return FreightRuleTypeEnum;
                }
            }
            return CREATED;
        }

        public int getStatus()
        {
            return status;
        }

        public void setStatus(int status)
        {
            this.status = status;
        }

        public String getBuyerDesc()
        {
            return buyerDesc;
        }

        public void setBuyerDesc(String buyerDesc)
        {
            this.buyerDesc = buyerDesc;
        }

        public String getSellerDesc()
        {
            return sellerDesc;
        }

        public void setSellerDesc(String sellerDesc)
        {
            this.sellerDesc = sellerDesc;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public boolean isMajor()
        {
            return major;
        }

        public void setMajor(boolean major)
        {
            this.major = major;
        }

        public OrderStepEnum getStep()
        {
            return step;
        }

        public void setStep(OrderStepEnum step)
        {
            this.step = step;
        }

        public static OrderStepEnum getStep(int status)
        {
            OrderStepEnum step = null;
            OrderStatusEnum statusEnum = get(status);
            if (statusEnum != null)
            {
                step = statusEnum.getStep();
            }
            return step;
        }

        public String getSysDesc()
        {
            return sysDesc;
        }

        public static OrderStatusEnum getMajorStatus(OrderStepEnum step)
        {
            OrderStatusEnum[] values = OrderStatusEnum.values();
            for (OrderStatusEnum statusEnum : values)
            {
                if (statusEnum.getStep() == step)
                {
                    return statusEnum;
                }
            }
            return null;
        }

        public static Map<Integer, String> getBuyerMajorStatusMap()
        {
            Map<Integer, String> map = new HashMap<Integer, String>();
            for (OrderStatusEnum statusEnum : values())
            {
                map.put(statusEnum.getStatus(), statusEnum.getBuyerDesc());
            }
            return map;
        }

        public static Map<Integer, String> getSellerMajorStatusMap()
        {
            Map<Integer, String> map = new HashMap<Integer, String>();
            for (OrderStatusEnum statusEnum : values())
            {
                map.put(statusEnum.getStatus(), statusEnum.getSellerDesc());
            }
            return map;
        }
    }

    private enum OrderEventEnum
    {
        CREATE("创建订单", OrderEventRoleEnum.BUYER),

        BUYER_CANCEL("取消订单", OrderEventRoleEnum.BUYER),

        BUYER_REVIEW("买家评价", OrderEventRoleEnum.BUYER),

        SELLER_CANCEL("取消订单", OrderEventRoleEnum.SELLER),

        PAY_FAILURE("支付失败", OrderEventRoleEnum.BUYER),

        PAY_SUCCESS("支付成功", OrderEventRoleEnum.BUYER),

        PAY_APPLY_KUPAY("提交零库担保支付申请", OrderEventRoleEnum.BUYER),

        PAY_REJECT_KUPAY("拒绝零库担保支付申请", OrderEventRoleEnum.SYSTEM),

        PAY_ACCEPT_KUPAY("通过零库担保支付申请", OrderEventRoleEnum.SYSTEM),

        CONFIRM("确认订单", OrderEventRoleEnum.SELLER),

        CONFIRM_TRANSFER("确认并转让订单", OrderEventRoleEnum.SELLER),

        SHIPPING("确认发货", OrderEventRoleEnum.SELLER),

        RECEIVE("确认收货", OrderEventRoleEnum.BUYER),

        MODIFY("修改订单", OrderEventRoleEnum.SELLER),

        REFUND_APPLY("发起退款申请", OrderEventRoleEnum.BUYER),

        REFUND_REJECT("拒绝退款申请", OrderEventRoleEnum.SELLER),

        REFUND_APPLY_CANCEL("取消退款申请", OrderEventRoleEnum.BUYER),

        REFUND_SUCCESS("完成退款", OrderEventRoleEnum.SYSTEM),

        REFUND_ACCEPT("同意退款申请", OrderEventRoleEnum.SELLER),

        EXPIRE("订单超时", OrderEventRoleEnum.SYSTEM),

        OFFLINE_CONFIRM("线下交易确认", OrderEventRoleEnum.SYSTEM),

        OFFLINE_APPLY("线下交易申请", OrderEventRoleEnum.BUYER),

        SYSTEM_CLOSE("系统关闭订单", OrderEventRoleEnum.SYSTEM);

        private String message;

        private OrderEventRoleEnum role;

        OrderEventEnum(String message, OrderEventRoleEnum role)
        {
            this.message = message;
            this.role = role;
        }

        public String getMessage()
        {
            return message;
        }

        public OrderEventRoleEnum getRole()
        {
            return role;
        }

        @Override
        public String toString()
        {
            return (getRole() == OrderEventRoleEnum.BUYER ? "买家" : "卖家") + getMessage();
        }
    }

    private enum OrderEventRoleEnum
    {
        BUYER,

        SELLER,

        SYSTEM;

    }

    private enum OrderStepEnum
    {
        CREATION(1, "提交订单", "买家提交订单"),

        PAY(2, "付款", "买家付款"),

        CONFIRM(3, "卖家确认订单", "确认订单", false),

        SHIPPED(4, "卖家发货", "发货"),

        RECEIVED(5, "确认收货", "买家收货"),

        REVIEWD(6, "评价", "买家评价"),

        BUYER_CANCEL(7, "买家取消订单", "买家取消订单"),

        SELLER_CANCEL(8, "卖家取消订单", "卖家取消订单"),

        REFUNDING(9, "退款申请中", "买家申请退款"),

        REFUND_SUCCESS(10, "退款完成", "交易关闭"),

        OFFLINE_AAPLY(11, "线下交易中", "买家申请线下交易"),

        OFFLINE_CONFIRM(12, "线下交易完成", "线下交易完成"),

        SYSTEM_CLOSE(13, "订单关闭", "订单关闭");

        private int value;

        private String buyerMessage;

        private String sellerMessage;

        private boolean buyerVisible;

        private boolean sellerVisible;

        OrderStepEnum(int value, String buyerMessage, String sellerMessage)
        {
            this(value, buyerMessage, sellerMessage, true);
        }

        OrderStepEnum(int value, String buyerMessage, String sellerMessage, boolean buyerVisible)
        {
            this(value, buyerMessage, sellerMessage, buyerVisible, true);

        }

        OrderStepEnum(int value, String buyerMessage, String sellerMessage, boolean buyerVisible, boolean sellerVisible)
        {
            this.value = value;
            this.buyerMessage = buyerMessage;
            this.sellerMessage = sellerMessage;
            this.buyerVisible = buyerVisible;
            this.sellerVisible = sellerVisible;
        }

        public String getBuyerMessage()
        {
            return buyerMessage;
        }

        public void setBuyerMessage(String buyerMessage)
        {
            this.buyerMessage = buyerMessage;
        }

        public String getSellerMessage()
        {
            return sellerMessage;
        }

        public void setSellerMessage(String sellerMessage)
        {
            this.sellerMessage = sellerMessage;
        }

        public int getValue()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        public static OrderStepEnum get(int value)
        {
            OrderStepEnum[] values = OrderStepEnum.values();
            for (OrderStepEnum stepEnum : values)
            {
                if (stepEnum.getValue() == value)
                {
                    return stepEnum;
                }
            }
            return null;
        }

        public boolean isBuyerVisible()
        {
            return buyerVisible;
        }

        public void setBuyerVisible(boolean buyerVisible)
        {
            this.buyerVisible = buyerVisible;
        }

        public boolean isSellerVisible()
        {
            return sellerVisible;
        }

        public void setSellerVisible(boolean sellerVisible)
        {
            this.sellerVisible = sellerVisible;
        }
    }
}
