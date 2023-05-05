package game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClueSheet extends JPanel {

    private JLabel nameLabel;
    private JPanel suspectsPanel, weaponsPanel, roomsPanel;

    public ClueSheet() {
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        suspectsPanel = new JPanel(new GridLayout(3, 2));
        suspectsPanel.setBorder(BorderFactory.createTitledBorder("Suspects"));
        suspectsPanel.add(new JLabel("Miss Scarlet: "));
        suspectsPanel.add(new JTextField());
        suspectsPanel.add(new JLabel("Colonel Mustard: "));
        suspectsPanel.add(new JTextField());
        suspectsPanel.add(new JLabel("Mrs. Peacock: "));
        suspectsPanel.add(new JTextField());
        suspectsPanel.add(new JLabel("Professor Plum: "));
        suspectsPanel.add(new JTextField());
        suspectsPanel.add(new JLabel("Reverend Green: "));
        suspectsPanel.add(new JTextField());
        suspectsPanel.add(new JLabel("Dr. Orchid: "));
        suspectsPanel.add(new JTextField());
        this.add(suspectsPanel);

        weaponsPanel = new JPanel(new GridLayout(3, 2));
        weaponsPanel.setBorder(BorderFactory.createTitledBorder("Weapons"));
        weaponsPanel.add(new JLabel("Candlestick: "));
        weaponsPanel.add(new JTextField());
        weaponsPanel.add(new JLabel("Knife: "));
        weaponsPanel.add(new JTextField());
        weaponsPanel.add(new JLabel("Revolver: "));
        weaponsPanel.add(new JTextField());
        weaponsPanel.add(new JLabel("Candlestick: "));
        weaponsPanel.add(new JTextField());
        weaponsPanel.add(new JLabel("Knife: "));
        weaponsPanel.add(new JTextField());
        weaponsPanel.add(new JLabel("Revolver: "));
        weaponsPanel.add(new JTextField());
        this.add(weaponsPanel);

        roomsPanel = new JPanel(new GridLayout(3, 3));
        roomsPanel.setBorder(BorderFactory.createTitledBorder("Rooms"));
        roomsPanel.add(new JLabel("Kitchen: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Ballroom: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Conservatory: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Dining Room: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Study: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Hall: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Lounge: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Billiard Room: "));
        roomsPanel.add(new JTextField());
        roomsPanel.add(new JLabel("Library: "));
        roomsPanel.add(new JTextField());
        this.add(roomsPanel);
    }
}

