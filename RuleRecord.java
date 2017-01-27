/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalProject;

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
 * @author Chika Sugnaoya
 */
@WebServlet(name = "RuleRecord", urlPatterns = {"/RuleRecord"})
public class RuleRecord extends HttpServlet {

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
     /*============= ここから =============*/
        PrintWriter out = response.getWriter();

        //コネクションとステートメントの宣言
        Connection con = null;
        Statement stmt0 = null;
        Statement stmt1 = null;

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SelectServlet</title>");
            out.println("</head>");
            out.println("<body>");
            

            //WebアプリケーションのときはClass.forNameが必要
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            int count=0,VD=0;
            double killRate =0,kill=0,death=0,VDRate=0;
            
                 
            //送信された値の取得
            request.setCharacterEncoding("UTF-8");
            String rule = request.getParameter("rule");
            out.println("<h1>"+rule+"の成績</h1>");
            
                    
           
            //データベース接続
            String driverUrl = "jdbc:derby://localhost:1527/SplatoonVDR";
            con = DriverManager.getConnection(driverUrl,"db","db");
            
            stmt0 = con.createStatement();
            String sql00 = "select * from SPR_DATA00 where RULE='"+rule+"'";
            ResultSet rs00 = stmt0.executeQuery(sql00);
            
            
            while(rs00.next()){
                
            String ID = Integer.toString(rs00.getInt("DATA_ID"));
            String stageName = rs00.getString("STAGE");
            kill += rs00.getInt("KILLSNUM"); 
            death += rs00.getInt("DEATHNUM");
            String mine = Integer.toString(rs00.getInt("MINECOUNT"));
            String opp = Integer.toString(rs00.getInt("OPPCOUNT"));
            VD += rs00.getInt("VD");
            count++;
            }

            killRate =((double)kill/(double)death);
            VDRate = (double)VD/count*100;
            out.println("<table>");
            out.println("<tr><td>平均のキルレート</td><td>"+killRate+"</td><tr>");
            out.println("<tr><td>"+rule+"の勝率</td><td>"+Math.round(VDRate)+"%</td><tr>");
            out.println("</table>");
            out.println("<br>");
            out.println("<hr>");
            
            rs00.close();
            //stmt0.close();
   
            stmt1 = con.createStatement();
            
          
            String sql01 = "select * from SPR_DATA00 where RULE='"+rule+"'";
            ResultSet rs01 = stmt1.executeQuery(sql01);
            
            out.println("<h2>"+rule+"の成績一覧</h2>");
            out.println("<hr>");
            
            while(rs01.next()){
            String ID = Integer.toString(rs01.getInt("DATA_ID"));
            String ruleName = rs01.getString("RULE"); 
            String stage = rs01.getString("STAGE");
            String killNum = Integer.toString(rs01.getInt("KILLSNUM")); 
            String deathNum = Integer.toString(rs01.getInt("DEATHNUM"));
            String mine = Integer.toString(rs01.getInt("MINECOUNT"));
            String opp = Integer.toString(rs01.getInt("OPPCOUNT"));
            int VorD = rs01.getInt("VD");
            
            
            //そのまま出力
            out.println("<table>");
            
            out.println("<tr><td>ステージ</td><td>"+stage+"</td></tr>");
            out.println("<tr><td>キル数</td><td>"+killNum+"</td></tr>");
            out.println("<tr><td>デス数</td><td>"+deathNum+"</td></tr>");
            out.println("<tr><td>味方のチームのカウント</td><td>"+mine+"</td></tr>");
            out.println("<tr><td>敵のチームのカウント</td><td>"+opp+"</td></tr>");
            out.println("<tr><td>勝敗</td><td>"+VorD+"</td></tr>");
            out.println("</table>");
            out.println("<hr>");
            
            
            
            }
            rs01.close();
            
            
            
            
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
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
        }
        //PrintWriterをclose
        out.close();

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
