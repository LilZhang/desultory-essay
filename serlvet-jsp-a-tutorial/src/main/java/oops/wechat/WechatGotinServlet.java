<<<<<<< 41b6f739233c6b9f970a79f9ed1db68b869eaec6
/*
=======
>>>>>>> 201706161956
package oops.wechat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/wechat_gotin")
public class WechatGotinServlet extends HttpServlet
{
<<<<<<< 41b6f739233c6b9f970a79f9ed1db68b869eaec6
    */
/*@Override
=======
    /*@Override
>>>>>>> 201706161956
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Map<String, String> infoMap = Utils.getReqInfoMap(req);

        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>");
        writer.print("<html><head></head><body><div>test</div>");
        writer.print("<table>");
        for (Map.Entry<String, String> entry : infoMap.entrySet())
        {
            writer.print("<tr>");
            writer.print("<td>");
            writer.print(entry.getKey());
            writer.print("</td>");
            writer.print("<td>");
            writer.print(entry.getValue());
            writer.print("</td>");
            writer.print("</tr>");
        }
        writer.print("</table></body></html>");

<<<<<<< 41b6f739233c6b9f970a79f9ed1db68b869eaec6
    }*//*

=======
    }*/
>>>>>>> 201706161956

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        connect(request,response);
    }

    private void connect(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("RemoteAddr: "+ request.getRemoteAddr());
        System.out.println("QueryString: "+ request.getQueryString());
        if(!accessing(request, response))
        {
            System.out.println("服务器接入失败.......");
            return ;
        }
        String echostr = getEchostr();

        if(echostr != null && !"".equals(echostr))
        {
            System.out.println("服务器接入生效..........");
            response.getWriter().print(echostr);//完成相互认证
        }
    }

    private boolean accessing(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if(isEmpty(signature))
        {
            return false;
        }
        if(isEmpty(timestamp))
        {
            return false;
        }
        if(isEmpty(nonce))
        {
            return false;
        }
        if(isEmpty(echostr))
        {
            return false;
        }
        String[] ArrTmp = { Token , timestamp, nonce };
        Arrays.sort(ArrTmp);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ArrTmp.length; i++)
        {
            sb.append(ArrTmp[i]);
        }
        String pwd = Encrypt(sb.toString());

        System.out.println("signature:"+signature+"timestamp:"+timestamp+"nonce:"+nonce+"pwd:"+pwd+"echostr:"+echostr);

        if(trim(pwd).equals(trim(signature)))
        {
            this.echostr = echostr;
            return true;
        }
        else
        {
            return false;
        }
    }

    private String Encrypt(String strSrc)
    {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try
        {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); //to HexString
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1)
            {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }


    public String getEchostr()
    {
        return echostr;
    }

    private boolean isEmpty(String str)
    {
        return null == str || "".equals(str) ? true : false;
    }

    private String trim(String str)
    {
        return null != str  ? str.trim() : str;
    }
<<<<<<< 41b6f739233c6b9f970a79f9ed1db68b869eaec6
}*/
=======
}
>>>>>>> 201706161956
