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
@WebServlet(urlPatterns = {"/DataManage"})
public class DataManage extends HttpServlet {

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
            String rule = request.getParameter("rule");
            String stageName = request.getParameter("stageName");
            String kill = request.getParameter("kill");
            String death = request.getParameter("death");
            String mineC = request.getParameter("mineC");
            String oppC = request.getParameter("oppC");
            String VorD,VorDString;
            //受け取った値から勝敗の計算
            int mineCountValue = Integer.parseInt(mineC);
            int oppCountValue = Integer.parseInt(oppC);
            if(mineCountValue > oppCountValue){
            VorD = "1";
            VorDString ="勝ち"; 
            }
            else{
            VorD ="0";
            VorDString ="負け";
            }
            
           // try{
            //データベース接続
            String driverUrl = "jdbc:derby://localhost:1527/SplatoonVDR";
            con = DriverManager.getConnection(driverUrl,"db","db");
            stmt = con.createStatement();
            
            //最後のdata_id取得する
            String sql00 = "select max(data_id)as MAXID from SPR_DATA00";
            ResultSet rs = stmt.executeQuery(sql00);
            int maxID = 0;
            if(rs.next()){
            maxID = rs.getInt("MAXID")+1;
            }
            
            String sql01 ="insert into SPR_DATA00 values("+maxID+",'"+rule+"','"+stageName+"',"+kill+","+death+","+mineC+","+oppC+","+VorD+")";
            int count =stmt.executeUpdate(sql01);
            rs.close();
            
            out.println("<h2>登録が完了しました。</h2>");
            
            
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
