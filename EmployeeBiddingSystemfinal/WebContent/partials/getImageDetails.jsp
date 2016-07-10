<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.DAO.DBConnectivity"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int img_id = Integer.parseInt(request.getParameter("imag_id"));
Connection con = DBConnectivity.connectDB();
ResultSet rs = null;
PreparedStatement pstmt = null;
OutputStream oImage;
try {
pstmt = con.prepareStatement("select IMG from product_details where id = ?");
pstmt.setInt(1, img_id);
rs = pstmt.executeQuery();
    if(rs.next()) {
        byte barray[] = rs.getBytes(1);
        response.setContentType("image/gif");
        oImage=response.getOutputStream();
        oImage.write(barray);
        oImage.flush();
        oImage.close();
    }
}
catch(Exception ex){
    //ex.printStackTrace();
}finally {
    try{
    if(con!=null)
       con.close();
    }catch(Exception ex){
       // ex.printStackTrace();
    }
}%>

</body>
</html>