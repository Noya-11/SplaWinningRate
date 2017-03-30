package SplaJava;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(urlPatterns = {"/AllMaterSearch"})
public class AllMaterSearch extends HttpServlet {

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
           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>全件表示</h1>");

            //WebアプリケーションのときはClass.forNameが必要
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            
            //データベース接続
            String driverUrl = "jdbc:derby://localhost:1527/SplatoonVDR";
            con = DriverManager.getConnection(driverUrl,"db","db");
            stmt = con.createStatement();
            
            //最後のdata_id取得する
            String sql00 = "select * from SPR_DATA00";
            ResultSet rs = stmt.executeQuery(sql00);
            
            
            while(rs.next()){
            String ID = Integer.toString(rs.getInt("DATA_ID"));
            String rule = rs.getString("RULE"); 
            String stageName = rs.getString("STAGE");
            String kill = Integer.toString(rs.getInt("KILLSNUM"));
            String death = Integer.toString(rs.getInt("DEATHNUM"));
            String mine = Integer.toString(rs.getInt("MINECOUNT"));
            String opp = Integer.toString(rs.getInt("OPPCOUNT"));
            int VD = rs.getInt("VD");
            String VorD;
            if(VD == 1){
               VorD = "勝ち";
            }
            else{ VorD="負け"; }
            out.println("<table>");
            out.println("<tr><td>データID</td><td>"+ID+"</td></tr>");
            out.println("<tr><td>ガチマッチルール</td><td>"+rule+"</td></tr>");
            out.println("<tr><td>ステージ</td><td>"+stageName+"</td></tr>");
            out.println("<tr><td>キル数</td><td>"+kill+"</td></tr>");
            out.println("<tr><td>デス数</td><td>"+death+"</td></tr>");
            out.println("<tr><td>味方のチームのカウント</td><td>"+mine+"</td></tr>");
            out.println("<tr><td>敵のチームのカウント</td><td>"+opp+"</td></tr>");
            out.println("<tr><td>勝敗</td><td>"+VorD+"</td></tr>");
            out.println("</table>");
            out.println("<hr>");
            }
            
            
            rs.close();
           
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
