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
        <title>ユーザー情報確認</title>
    </head>
    <body>
        <h1>入力した内容が以下で宜しければ<b>進む</b>ボタンを押してください</h1>
        <b>入力情報　：　<%= userName  %></b>
        <form name="確認ページ" action="./UserNameConfir" method="POST"> 
            
            <input type="submit" name="submitBtn" value="進む"/>
        </form>
    </body>
</html>
