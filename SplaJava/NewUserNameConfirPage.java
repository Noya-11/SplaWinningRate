/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplaJava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author noya
 */
@WebServlet(name = "NewUserNameConfirPage", urlPatterns = {"/NewUserNameConfirPage"})
public class NewUserNameConfirPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //コネクションとステートメントの宣言
        Connection con = null;
        Statement stmt0 = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        Statement stmt3 = null;

        String users[] = new String[10000]; //userの名前調べるのに使う
        int count = 0;
        boolean registFlag = true;

        try {

            //WebアプリケーションのときはClass.forNameが必要
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //送信された値の取得
            request.setCharacterEncoding("UTF-8");
            String newUserName = request.getParameter("newUserName");

            // try{
            //データベース接続
            String driverUrl = "jdbc:derby://localhost:1527/SplatoonRDB";
            con = DriverManager.getConnection(driverUrl, "noya", "db");
            out.println("success");
            out.println("<hr><br>");

            stmt0 = con.createStatement();
            //最後のdata_id取得する

            String sql00 = "select max(user_id)as MAXID from USER_ACCOUNT";
            ResultSet rs00 = stmt0.executeQuery(sql00);
            int maxID = 0;
            if (rs00.next()) {
                maxID = rs00.getInt("MAXID") + 1;
            }
            //INSERT INTO NOYA.USER_ACCOUNT(USER_ID,USER_NAME) VALUES (0,'testUser');

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SelectServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>データーベースへ接続……</h1>");

            stmt1 = con.createStatement();
            String sql01 = "select * from USER_ACCOUNT";
            ResultSet rs01 = stmt1.executeQuery(sql01);

            while (rs01.next()) {
                String userName = rs01.getString("USER_NAME");
                users[count] = userName;
                out.println(users[count] + "<hr><br>");
                count++;

            }

            rs01.close();

            for (int i = 0; i < users.length; i++) {
                if (newUserName.equals(users[i])) { // 既に使用されている場合。

                    out.println("<b>既に使用されているユーザー名です。</b>");
                    out.println("<a href='/SplatoonRecode//webPage/RegistrationPage.html'>登録ぺージへ戻る</a>");
                    registFlag = false;
                } else  {
                    //何もしない。
                }
            }

            if (registFlag) {

                stmt2 = con.createStatement();
                String sql02 = "insert into USER_ACCOUNT values(" + maxID + ",'" + newUserName + "')";
                int regist = stmt2.executeUpdate(sql02);

                stmt3 = con.createStatement();
                String sql03 = "create table " + newUserName + "(DATA_ID integer primary key,\n"
                        + "RULE VarChar(255),\n"
                        + "STAGE VarChar(255),\n"
                        + "KILLSNUM integer,\n"
                        + "DEATHNUM integer,\n"
                        + "MINECOUNT integer,\n"
                        + "OPPCOUNT  integer,\n"
                        + "VD integer)";
                int registTable = stmt3.executeUpdate(sql03);

                out.println("<b>登録が完了しました。</b><br>");
            }

            //out.println("sc");
            //out.println("<h2>戦績ぺージへ移動します。</h2>");
            out.println("<h2></h2>");

            out.println("<hr>");
            out.println("<a href='/SplatoonRecorde/webPage/TopPage.html'>トップページへ戻る</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            //サーブレット内での例外をアプリケーションのエラーとして表示

            out.println(e); // ブラウザ上に出力
            // out.println("<meta http-equiv= \"refresh\" content= \"3;URL=>\"/SplatoonRecode/RegistrationPage.html");
            throw new ServletException(e);

        } finally {
            //例外が発生したときでも確実にデータベースから切断
            if (stmt0 != null) {
                try {
                    stmt0.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
            if (stmt1 != null) {
                try {
                    stmt1.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
            if (stmt2 != null) {
                try {
                    stmt2.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
