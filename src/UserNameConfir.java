/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplaJava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.apache.tomcat.jni.Proc.kill;

/**
 *
 * @author noya
 */
@WebServlet(name = "UserNameConfir", urlPatterns = {"/UserNameConfir"})
public class UserNameConfir extends HttpServlet {

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
        Statement stmt = null;

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SelectServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>データーベースへ接続……</h1>");

            //WebアプリケーションのときはClass.forNameが必要
            Class.forName("org.apache.derby.jdbc.ClientDriver");

                        
            //送信された値の取得
            request.setCharacterEncoding("UTF-8");
            String userName = request.getParameter("userName");
            
            
           // try{
            //データベース接続
            String driverUrl = "jdbc:derby://localhost:1527/SplatoonRDB";
            con = DriverManager.getConnection(driverUrl,"noya","db");
            stmt = con.createStatement();
        /*    
            //最後のdata_id取得する
            String sql00 = "select max(user_id)as MAXID from USER_ACCOUNT";
            ResultSet rs = stmt.executeQuery(sql00);
            int maxID = 0;
            if(rs.next()){
            maxID = rs.getInt("MAXID")+1;
            }
            INSERT INTO NOYA.USER_ACCOUNT(USER_ID,USER_NAME) VALUES (0,'testUser');
            
          */  
        
            String sql00 ="select * from NOYA.USER_ACCOUNT sales where USER_NAME like '"+userName +"'";
            ResultSet rs = stmt.executeQuery(sql00);
            int count =stmt.executeUpdate(sql00);
            rs.close();
            
            
            
            out.println("<h2>戦績ぺージへ移動します。</h2>");
            out.println("<h2></h2>");
            
            out.println("<hr>");
            out.println("<a href='/ap4_www/finalProject/topPage.html'>トップページへ戻る</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            //サーブレット内での例外をアプリケーションのエラーとして表示
            out.println(e); // ブラウザ上に出力
            throw new ServletException(e);
        } finally {
            //例外が発生したときでも確実にデータベースから切断
            if (stmt != null) {
                try {
                    stmt.close();
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
