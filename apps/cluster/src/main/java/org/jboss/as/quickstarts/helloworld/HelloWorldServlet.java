/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/cluster")
public class HelloWorldServlet extends HttpServlet {

  static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

  static String PAGE_FOOTER = "</body></html>";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    String serverName =  InetAddress.getLocalHost().getHostName();
    String serverAddress = InetAddress.getLocalHost().getHostAddress();
    String hostLine = "<p>I am running on host " + serverName + " (" + serverAddress + ")" + "</p>";

    HttpSession session = req.getSession(false);
    String sessionId;
    Integer count = 1;
    if (session == null) {

      session = req.getSession();
      sessionId = session.getId();
      session.setAttribute("counter", count);
    }

    else {
      sessionId = session.getId();
      count = (Integer) session.getAttribute("counter");
      ++count;
      session.setAttribute("counter", count);
    }

    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    writer.println(PAGE_HEADER);
    writer.println("<h1>Cluster Demo App</h1>");
    writer.println("<p>Session ID: " + sessionId + "</p>");
    writer.println("<p>Visit Count: " + count + "</p>");
    writer.println(hostLine);
    writer.println(PAGE_FOOTER);
    writer.close();
  }

}
