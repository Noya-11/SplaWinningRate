<%-- 
    Document   : InputPage
    Created on : 2017/04/02, 15:22:45
    Author     : noya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    request.setCharacterEncoding("UTF-8");
    String userName = request.getParameter("userName");

    if (userName == null) {
        userName = "testUser";
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <h1>Splatoon成績管理</h1>
        <p><%= userName%></p>
        <form name="入力ページ" action="ConfirPage.jsp" method="post">
            <input type="hidden" name="userName" value="<%= userName%>">
            <p>各項目にプレイしたデータを入力してください</p>

            <table >
                <tr><td>遊んだルール</td>
                    <td><input type="radio" name="rule" value="ガチエリア" checked="checked"/>ガチエリア<br>
                        <input type="radio" name="rule" value="ガチヤグラ"/>ガチヤグラ<br>
                        <input type="radio" name="rule" value="ガチホコ"/>ガチホコ</td></tr><hr>

                <tr><td>遊んだステージ名</td>
                    <td><select name="StageName" size="1">
                <option value="デカライン高架下" selected="selected">デカライン高架下</option>
                <option value="シオノメ油田">シオノメ油田</option>
                <option value="Bバスパーク">Bバスパーク</option>
                <option value="ハコフグ倉庫">ハコフグ倉庫</option>
                <option value="アロワナモール">アロワナモール</option>
                <option value="ホッケふ頭">ホッケふ頭</option>
                <option value="モズク農園">モズク農園</option>
                <option value="ネギトロ炭鉱">ネギトロ炭鉱</option>
                <option value="タチウオパーキング">タチウオパーキング</option>
                <option value="モンガラキャンプ場">モンガラキャンプ場</option>
                <option value="ヒラメが丘団地">ヒラメが丘団地</option>
                <option value="マサバ海峡大橋">マサバ海峡大橋</option>
                <option value="キンメダイ美術館">キンメダイ美術館</option>
                <option value="マヒマヒリゾート＆スパ">マヒマヒリゾート＆スパ</option>
                <option value="ショッツル鉱山">ショッツル鉱山</option>
                <option value="アンチョビットゲームズ">アンチョビットゲームズ</option>
                </select></td></tr>

                <tr><td>キル数入力</td>
                    <td><input type="text" name="killsData"></td></tr>
                <tr><td>デス数入力</td>
                    <td><input type="text" name="deathData"></td></tr>

                <tr><td>味方チームのカウント</td>
                    <td><input type="text" name="mineCountData"></td></tr>

                <tr><td>敵チームのカウント</td>
                    <td><input type="text" name="oppCountData"></td></tr>

            </table>

            <input type="submit" name="submitBtn" value="追加"/>
            <input type="reset" name="resetBtn" value="やりなおす"/>
        </form>    
        <hr>

        <form name="入力ページ" action="RecordePage.jsp" method="post">
            <input type="hidden" name="userName" value="<%= userName%>">
            <input type="submit" name="submitBtn" value="成績を見る"/>
        </form>
        <hr>
        <hr>
        <a href="ManagePage.html">管理ページへ</a>

    </body>
</html>

