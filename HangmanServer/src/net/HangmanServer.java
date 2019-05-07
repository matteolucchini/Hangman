/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import console.LocalPlayer;
import console.RemotePlayer;
import hangman.Hangman;
import hangman.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class HangmanServer {
    private ServerSocket serverSocket;
    private Socket client;
    private PrintWriter out;
    private BufferedReader br;
    private Player player;
    private Hangman game;

    public HangmanServer() throws Exception {
        int port = 8888;
        serverSocket = new ServerSocket(port);
    }

    public static void main(String[] args) throws Exception {
        HangmanServer hangman = new HangmanServer();
        hangman.listen();
    }

    public void listen() throws Exception {
        String data = null;
        client = serverSocket.accept();
        game = new Hangman();
        out = new PrintWriter(client.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        player = new RemotePlayer(br, out);
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("Connesso con: " + clientAddress);
        play();
    }

    private void play() throws Exception {
        game.playGame(player);
    }
    
}
