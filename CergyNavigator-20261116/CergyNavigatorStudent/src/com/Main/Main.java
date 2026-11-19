package com.Main;

import com.GUI.GUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
        //   STIFAPIRequest stifapiRequest=new STIFAPIRequest();
        //   System.out.println(stifapiRequest.APIHTTPRequest());
    }
}
