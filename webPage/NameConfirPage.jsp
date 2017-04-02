<%-- 
    Document   : NameConfirPage
    Created on : 2017/03/28, 16:36:23
    Author     : noya
--%>

<%

 request.setCharacterEncoding("UTF-8");
            String userName = request.getParameter("userName");
            
            

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>入力内容確認</title>
    </head>
    <body>
      <h1>登録内容を確認できたら進むボタンを押してください。</h1>
        <b>入力したユーザー名　：<%= userName  %></b>
        
        <form name="内容確認" action="../UserNameConfir" method="POST"> 
            <input type = "hidden" name = "userName" value=" <%= userName%>">
            <input type="submit" name="submitBtn" value="進む"/>
        </form>
    </body>
</html>
