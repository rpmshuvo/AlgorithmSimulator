package com.JS.AlgorithmSimulator.OthersMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.JS.AlgorithmSimulator.Custom.Button;
import com.JS.AlgorithmSimulator.Custom.CustomJOptionPane;
import com.JS.AlgorithmSimulator.MainMenu.MainMenu;

public class ExternalsLinksMenu extends JPanel {
	private static final long serialVersionUID = 1L;

    private final String[] LIST_DATA = {"Searching", "Sorting"};
    
    private final String[] LIST_SECONDARY_DATA_SORTING = {"Bubble Sort", "Selection Sort"};
    private final String[] LIST_SECONDARY_DATA_SEARCHING = {"Linear Search", "Binary Search"};
    
    private final int BORDER_THICKNESS_BORDERLAYOUT = 5;
    private final int LIST_VISIBLE_ROW_COUNT = 10;
    
    private final Font LIST_FONT = new Font("Calibiri", Font.BOLD, 25);
    private final Font LABEL_FONT = new Font("courier", Font.BOLD, 30);
    
    private final String TITLE_TEXT = "\"External Links\"";
    private final String ERROR_TITLE = "ERROR !!!";
    private final String ERROR_LOG = "Please select both options from the list.";
    private final String OPEN_BUTTON = "Open in browser";
    private final String BACK_BUTTON = "Back";
    
    private final Color LIST_BG = new Color(65, 175, 245);
    private final Color BACKGROUND_COLOR = Color.WHITE;
    
    private final String URL_LINEAR_SEARCH = "https://en.wikipedia.org/wiki/Linear_search";
    private final String URL_BINARY_SEARCH = "https://en.wikipedia.org/wiki/Binary_search_algorithm";
    private final String URL_BUBBLE_SORT = "https://en.wikipedia.org/wiki/Bubble_sort";
    private final String URL_SELECTION_SORT = "https://en.wikipedia.org/wiki/Selection_sort";

    private JList<String> jList; 
    private JList<String> secondJList;
    
    private JPanel mainPanel;
    private JPanel listPanel = new JPanel();
    
    private Button btnBack;
    private Button btnOpenInBrowser;
    
    private JLabel lblCatagories;

    public ExternalsLinksMenu(final JPanel mainPanel) {
    	this.mainPanel = mainPanel;
    	listPanel.setBackground(BACKGROUND_COLOR);
    	setBackground(BACKGROUND_COLOR);
    	
    	jList = new JList<>(LIST_DATA);
    	secondJList = new JList<>();
    	
        initialize_All();
    }

	private void initialize_All() {
		
		jList.setFont(LIST_FONT);
        jList.setPrototypeCellValue("ABCDEFGHIJKLMNOP ABCDE");
        jList.setVisibleRowCount(LIST_VISIBLE_ROW_COUNT);
        jList.setBackground(LIST_BG);
        
        jList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				///Add Main JList Here
				if(jList.getSelectedValue().equals("Searching")){
					secondJList.setListData(LIST_SECONDARY_DATA_SEARCHING);
				}
				else if (jList.getSelectedValue().equals("Sorting")) {
					secondJList.setListData(LIST_SECONDARY_DATA_SORTING);
				}
			}
		});
        
        secondJList.setFont(LIST_FONT);
        secondJList.setPrototypeCellValue("ABCDEFGHIJKLMNOP ABCDE");
        secondJList.setVisibleRowCount(LIST_VISIBLE_ROW_COUNT);
        secondJList.setBackground(LIST_BG);
        
        

        lblCatagories = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
        lblCatagories.setFont(LABEL_FONT);

        btnBack = new Button(BACK_BUTTON);
        btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				removeAll();
				new MainMenu(mainPanel);
			}
		});
        
        btnOpenInBrowser = new Button(OPEN_BUTTON);
        btnOpenInBrowser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jList.isSelectionEmpty() | secondJList.isSelectionEmpty()) {
					new CustomJOptionPane(ERROR_TITLE, ERROR_LOG, "Ok");
					
				}else {
					///Add URL to Here for SecondJList
					if(secondJList.getSelectedValue().equals("Linear Search")){
						goWebsite(URL_LINEAR_SEARCH);
					}else if (secondJList.getSelectedValue().equals("Binary Search")) {
						goWebsite(URL_BINARY_SEARCH);
					}else if (secondJList.getSelectedValue().equals("Bubble Sort")) {
						goWebsite(URL_BUBBLE_SORT);
					}else if (secondJList.getSelectedValue().equals("Selection Sort")) {
						goWebsite(URL_SELECTION_SORT);
					}
				}
			}
		});

        setBorder(BorderFactory.createEmptyBorder(BORDER_THICKNESS_BORDERLAYOUT, BORDER_THICKNESS_BORDERLAYOUT, BORDER_THICKNESS_BORDERLAYOUT, BORDER_THICKNESS_BORDERLAYOUT));
        setLayout(new BorderLayout(BORDER_THICKNESS_BORDERLAYOUT, BORDER_THICKNESS_BORDERLAYOUT));
        add(lblCatagories, BorderLayout.PAGE_START);
        add(jList, BorderLayout.WEST);
        //add(new JScrollPane(jList), BorderLayout.WEST);
        
        listPanel.setLayout(new BorderLayout(BORDER_THICKNESS_BORDERLAYOUT, BORDER_THICKNESS_BORDERLAYOUT));
        add(listPanel, BorderLayout.CENTER);
        listPanel.add(secondJList, BorderLayout.CENTER);
        //listPanel.add(new JScrollPane(secondJList), BorderLayout.CENTER);
        listPanel.add(btnOpenInBrowser, BorderLayout.PAGE_END);
        add(btnBack, BorderLayout.PAGE_END);
	}
	
	private void goWebsite(final String url) {
        try {
        	Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException | IOException ex) {
        	ex.printStackTrace();
        }
    }
}
