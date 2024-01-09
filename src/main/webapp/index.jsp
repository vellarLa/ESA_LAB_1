<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.javaee.data.Ticket" %>
<%@ page import="com.example.javaee.dto.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cinema</title>
</head>
<body style="background: bisque">
<h1 style="color: coral; font-family: 'Bodoni MT Black',serif"><%= "Welcome to the web-site of \"5 Stars\" cinema!" %></h1>
<br/>
<hr>
<%
  VisitorDto visitor = (VisitorDto) request.getAttribute("visitor");
  FilmDto film = (FilmDto) request.getAttribute("film");
  TimetableDto timetable = (TimetableDto) request.getAttribute("timetable");
  List<FilmDto> filmDtos = (List<FilmDto>) request.getAttribute("filmsList");
  List<TimetableDto> timetableDtos = (List<TimetableDto>) request.getAttribute("timetables");
  List<SeatDto> seatDtos = (List<SeatDto>) request.getAttribute("seats");
  List<TicketDto> myTicket = (List<TicketDto>) request.getAttribute("myTickets");
  if(visitor == null) {
    out.println("<p>" + "<h2>Enter your name please and mark if you have any benefits</h2>" + "</p>");
    out.println("<form action=\"visitor\" method=\"post\">\n" +
            "  <label for=\"name\" style=\"margin: 5%\">Name:</label>\n" +
            "  <input type=\"text\" id=\"name\" name=\"name\" required>\n" +
            "  <br>\n" +
            "  <label for=\"benefits\" style=\"margin: 5%\">Benefits:</label>\n" +
            "  <input type=\"checkbox\" id=\"benefits\" name=\"benefits\">\n" +
            "  <br>\n" +
            "  <input type=\"submit\" value=\"further...\" style=\"margin-left: 20%; font-family: Arial,serif\">\n" +
            "</form>");
  }
  else {
    out.println("<p>" + "Register by " + visitor.getName() + (visitor.getBenefits() ? " with" :
            " without") + " benefits" + "</p>");
    if(filmDtos != null && !filmDtos.isEmpty()) {
      out.println("<p>" + "<h3>Select the film you want</h3>" + "</p>");
      for (FilmDto filmDto : filmDtos) {
        out.println(
                "<a href=\"film?filmId=" + filmDto.getId() + "&visitorId=" + visitor.getId() + "\">" + filmDto.getName() +"</a>"
        );
      }
    }
    if (film != null) {
      out.println("<p>" + "Film: " + film.getName() + "</p>");
      if (timetableDtos != null && !timetableDtos.isEmpty()) {
        out.println("<p>" + "<h3>Select time you want</h3>" + "</p>");
        for (TimetableDto timetableDto : timetableDtos) {
          out.println(
                  "<a href=\"timetable?filmId=" + film.getId() + "&visitorId=" + visitor.getId() + "&timetableId=" + timetableDto.getId() + "\">" + timetableDto.getDate() +"</a>"
          );
        }
      }
      else if (timetable == null) {
        out.println("<p>" + "<h3>There are no any seances</h3>" + "</p>");
      }
    }
    if (film != null && timetable != null) {
      out.println("<p>" + "Timetable: " + timetable.getDate() + "</p>");
      if (seatDtos != null && !seatDtos.isEmpty()) {
        out.println("<table>\n");
        for (SeatDto seatDto : seatDtos) {
          out.println(
                  "<tr>\n" +
                  "<td>" + "Seat: " + seatDto.getSeat()
                          + "   Row: " + seatDto.getRow() + "</td>\n" +
                  "<td><a href=\"ticket?filmId=" + film.getId() + "&visitorId=" + visitor.getId()
                          + "&timetableId=" + timetable.getId() + "&seat=" + seatDto.getSeat()
                          + "&row=" + seatDto.getRow() + "\">" + "buy" +"</a></td>\n"
                  + "</tr>\n"
          );
        }
        out.println("</table>");
      }
      else {
        out.println("<p>" + "<h3>There are not free seats</h3>" + "</p>");
      }
    }
    out.println("<br>\n");
    out.println("<a href=\"my-ticket" + "?visitorId=" + visitor.getId()
            + ((film != null) ? ("&filmId=" + film.getId()) : "")
            + (timetable != null ? ("&timetableId=" + timetable.getId()) : "") + "\">My tickets</a>");
    if (myTicket != null){
      if (! myTicket.isEmpty()) {
        out.println("<table>\n");
        for (TicketDto ticketDto : myTicket) {
          out.println(
                  "<tr>\n" +
                          "<td>" + "Film: " + ticketDto.getTimetable().getFilm().getName() + "</td>\n" +
                          "<td>" + "   Date: " + ticketDto.getTimetable().getDate() + "</td>\n" +
                          "<td>" + "   Cost: " + ticketDto.getCost() + "</td>\n" +
                          "<td>" +  "   Seat: " + ticketDto.getSeat()
                          + " Row: " + ticketDto.getRow() +  "</td>\n"
                          + "<td><form action=\"" +
                          "my-ticket" +
                          "\" method=\"post\">\n" +
                          "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"ticketToDelete\" value=\""+ ticketDto.getId() +"\">\n" +
                          "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"visitorId\" value=\""+ visitor.getId() +"\">\n" +
                          ((film != null) ? (
                                  "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"filmId\" value=\""+ film.getId() +"\">\n"
                          ) : "") +
                          (timetable != null ? (
                                  "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"timetableId\" value=\""+ timetable.getId() +"\">\n"
                          ) : "") +
                          "    <input type=\"submit\" value=\"delete\"/>\n" +
                          "</form></td>"
                          + "</tr>\n"
          );
        }
        out.println("</table>");
      }
      else {
        out.println("<p>" + "<h3>There are not no tickets. Buy any new!</h3>" + "</p>");
      }
    }
  }
%>





</body>
</html>