<%-- 
    Document   : RecordePage
    Created on : 2017/04/02, 15:53:48
    Author     : noya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    request.setCharacterEncoding("UTF-8");
    String userName = request.getParameter("userName");
%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h2>今までの成績を見る</h2>
        <p><b>全件一覧はこちら</b></p>
        <form name="全件検索ボタン" action="../AllRecorde" method="post">
            <input type="hidden" name="userName" value="<%= userName%>">
            <input type="submit" name="submitBtn" value="戦績一覧"/>
            
        </form>

        <hr><hr>
        <p><b>ルールごとに成績を見たい場合はルールのみを、ステージごとに見たい場合はステージのみを選択して決定ボタンを押してください。</b></p>
        <br><br>
        <p>ルールごとの成績を見る</p>
        <form name="入力ページ" action="../RuleRecorde" method="post">
            <input type="hidden" name="userName" value="<%= userName%>">
            <table>
                <tr><td>ルール名を選択してください</td>
                    <td><select name="rule" size="1">
                            <option value="ガチエリア">ガチエリア</option>
                            <option value="ガチヤグラ">ガチヤグラ</option>
                            <option value="ガチホコ">ガチホコ</option>

                        </select></td></tr>

            </table>
            <input type="submit" name="submitBtn" value="決定"/>
            <input type="reset" name="resetBtn" value="変更"/>
        </form>
        <br>
        <hr>
        <p>ステージごとの成績を見る</p>

        <form name="入力ページ" action="../StageRecorde" method="post">
            <input type="hidden" name="userName" value="<%= userName%>">
            <table>
                <tr><td>ステージ名を選択してください</td>
                    <td><select name="StageName" size="1">
                            <option value="デカライン高架下">デカライン高架下</option>
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

            </table>
            <input type="submit" name="submitBtn" value="決定"/>
            <input type="reset" name="resetBtn" value="変更"/>
        </form>
    </body>
</html>
