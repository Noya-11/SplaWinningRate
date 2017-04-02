<%-- 
    Document   : ConfirPage
    Created on : 2017/04/02, 15:24:59
    Author     : noya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    request.setCharacterEncoding("UTF-8");
    String userName = request.getParameter("userName");
    String rule = request.getParameter("rule");
    String StageName = request.getParameter("StageName");
    String kill = request.getParameter("killsData");
    String death = request.getParameter("deathData");
    String mineC = request.getParameter("mineCountData");
    String oppC = request.getParameter("oppCountData");
    String VorD, VorDString;
    //受け取った値から勝敗の計算
    int mineCountValue = Integer.parseInt(mineC);
    int oppCountValue = Integer.parseInt(oppC);
    if (mineCountValue > oppCountValue) {
        VorD = "1";
        VorDString = "勝ち";
    } else {
        VorD = "0";
        VorDString = "負け";
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>確認ページ</title>
    </head>
    <body>
        <h1>以下のデータを登録します。</h1>
        <p>確認できたら追加ボタンを押してください。</p>
        <form name="確認ページ" action="../DataManage" method="POST">

            <input type="hidden" name="userName" value="<%=userName%>">
            <table>
                <tr><td>ガチマッチルール</td><td><%= rule%></td></tr>
                <input type="hidden" name="rule" value="<%=rule%>">
                <tr><td>ステージ</td><td><%= StageName%></td></tr>
                <input type="hidden" name="stageName" value="<%=StageName%>">
                <tr><td>キル数</td><td><%= kill%></td></tr>
                <input type="hidden" name="kill" value="<%=kill%>">
                <tr><td>デス数</td><td><%= death%></td></tr>
                <input type="hidden" name="death" value="<%=death%>">
                <tr><td>味方のカウント数</td><td><%= mineC%></td></tr>
                <input type="hidden" name="mineC" value="<%=mineC%>">
                <tr><td>敵のカウント数</td><td><%= oppC%></td></tr>
                <input type="hidden" name="oppC" value="<%=oppC%>">
                <tr><td>勝敗</td><td><%= VorDString%></td></tr>
                <input type="hidden" name="VorD" value="<%=VorD%>">
            </table>
            <input type="submit" name="submitBtn" value="追加"/>
        </form>

        <a href="InputPage.jsp">戻る</a><br>
        
        
        
    </body>
</html>
