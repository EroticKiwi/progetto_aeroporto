package utility_classes;

import java.awt.GridBagLayout;

import javax.swing.*;

import custom_components.*;
import dialogs.LoginDialog;
import frames.FormFrame;
import frames.MainFrame;

public class ComponentCreator {
	
	public static JFrame CreateFrame() {
		JFrame frame = new JFrame("Test window");
		frame.setVisible(true); // Ogni frame (finestra) alla sua creazione parte da "spenta" e deve essere accesa quando la vogliamo visualizzare.
		return frame;
	}
	
	public static JFrame CreateFrame(int width, int height) {
		JFrame frame = CreateFrame();
		FrameUtility.SetFrameSize(frame, width, height);
		return frame;
	}
	
	public static MainFrame CreateMainFrame() {
		MainFrame frame = new MainFrame();
		frame.setTitle("Main Window");
		frame.setVisible(true);
		return frame;
	}
	
	public static MainFrame CreateMainFrame(int width, int height) {
		MainFrame frame = CreateMainFrame();
		FrameUtility.SetFrameSize(frame, width, height);
		return frame;
	}
	
	public static FormFrame CreateFormFrame() {
		FormFrame frame = new FormFrame();
		frame.setTitle("Form Window");
		frame.setVisible(true);
		return frame;
	}
	
	public static FormFrame CreateFormFrame(int width, int height) {
		FormFrame frame = CreateFormFrame();
		frame.setSize(width, height);
		return frame;
	}
	
	public static JFrame CreateFrame_GridBagLayout() {
		JFrame frame = CreateFrame();
		frame.setLayout(new GridBagLayout());
		return frame;
	}
	
	public static JFrame CreateFrame_GridBagLayout(int width, int height) {
		JFrame frame = CreateFrame(width, height);
		frame.setLayout(new GridBagLayout());
		return frame;
	}
	
	public static JPanel CreatePanel() {
		JPanel panel = new JPanel(); // I panel sono simili ai DIV in HTML. Raccolgono insieme dei componenti GUI.
		panel.setVisible(true);
		return panel;
	}
	
	// Crea un panel dove gli oggetti sono allineati dall'alto verso il basso
	public static JPanel CreatePanel_VerticalAlignment() {
		JPanel panel = CreatePanel();
		JPanelUtility.SetVerticalAlignment(panel);
		return panel;
	}
	
	public static JLabel CreateJLabel_Centered(String labelText) {
		JLabel label = new JLabel(labelText);
		JLabelUtility.SetAlignment_Center(label);
		return label;
	}
	
	public static JButton CreateButton() {
		JButton button = new JButton();
		button.setFocusPainted(false);
		return button;
	}
	
	public static JButton CreateButton(String text) {
		JButton button = CreateButton();
		button.setText(text);
		return button;
	}
	
	public static JRoundedButton CreateRoundedButton() {
		JRoundedButton button = new JRoundedButton();
		return button;
	}
	
	public static JRoundedButton CreateRoundedButton(String text) {
		JRoundedButton button = CreateRoundedButton();
		button.setText(text);
		return button;
	}
	
	public static JRoundedButton CreateRoundedButton(String text, int width, int height) {
		JRoundedButton button = CreateRoundedButton(text);
		button.setSize(width, height);
		return button;
	}
	
	public static JDialog CreateDialog() {
		JDialog dialog = new JDialog();
		dialog.setVisible(true);
		return dialog;
	}
	
	public static LoginDialog CreateLoginDialog() {
		LoginDialog dialog = new LoginDialog();
		dialog.setTitle("Login");
		dialog.setVisible(true);
		return dialog;
	}
	
	public static LoginDialog CreateLoginDialog(int width, int height) {
		LoginDialog dialog = CreateLoginDialog();
		dialog.setSize(width, height);
		return dialog;
	}
}
