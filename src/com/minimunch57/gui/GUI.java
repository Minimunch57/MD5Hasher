package com.minimunch57.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author Minimunch57
 *
 */
public class GUI extends JFrame {

	/** The <tt>GUI</tt>'s unique serial. */
	private static final long serialVersionUID = 4858561359328732866L;
	
	/** A <tt>JPanel</tt> responsible for holding the content within the frame. */
	private JPanel contentPane;
	/** A <tt>JTextArea</tt> used for entering and receiving hash text. */
	private JTextArea textArea = null;
	/** A <tt>JButton</tt> used for hashing the contents of the <code>textArea</code>. */
	private JButton btnHash = null;

	
	//	Main Method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//	Start the GUI
					new GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * <ul>
	 * <p>	<b><i>GUI</i></b>
	 * <p>	<code>public GUI()</code>
	 * <p>	Creates a new <tt>GUI</tt>.
	 * </ul>
	 */
	public GUI() {
		this.setTitle("MD5 Hasher");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/com/minimunch57/images/icon64.png")));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 450);
		
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("MD5 Hasher");
		lblTitle.setFont(new Font("DialogInput", Font.PLAIN, 40));
		lblTitle.setToolTipText("MD5 Hasher by Minimunch57");
		lblTitle.setFocusable(false);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(5, 5, 590, 62);
		contentPane.add(lblTitle);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("DialogInput", Font.PLAIN, 20));
		textArea.setToolTipText("Type what you would like to be hashed here.");
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBackground(SystemColor.menu);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(5, 69, 575, 265);
		contentPane.add(scrollPane);
		
		btnHash = new JButton("Hash!");
		btnHash.addActionListener((actionEvent) -> {
			try {
				textArea.setText(hashMD5(textArea.getText()));
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
			}
		});
		btnHash.setFont(new Font("Arial", Font.BOLD, 35));
		btnHash.setToolTipText("Hash what is in the text box.");
		btnHash.setFocusable(false);
		btnHash.setFocusPainted(false);
		btnHash.setForeground(Color.WHITE);
		btnHash.setBackground(Color.GREEN.darker());
		btnHash.setBorder(null);
		btnHash.setBounds(5, 338, 574, 56);
		contentPane.add(btnHash);
		
		this.setVisible(true);
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>hashMD5</i></b>
	 * <p>	<code>private String hashMD5(String input) throws NoSuchAlgorithmException</code>
	 * <p>	Hashes the passed <tt>String</tt> using the MD5 algorithm and returns it as a <tt>String</tt>.
	 * @param input - a <tt>String</tt> containing the text to be hashed.
	 * @return a <tt>String</tt> with the hashed text.
	 * @throws NoSuchAlgorithmException thrown if unable to get an instance of the MD5 algorithm.
	 * </ul>
	 */
	private String hashMD5(String input) throws NoSuchAlgorithmException {
	    final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    final byte[] digest = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
		return String.format("%032x", new BigInteger(1, digest));
	}
}
