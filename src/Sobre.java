import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setResizable(false);
		setModal(true);
		setTitle("Info");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Buscador de Cep - v1.0");
			lblNewLabel.setBounds(74, 33, 139, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblauthor = new JLabel("@Author Professor Jose de Assis");
			lblauthor.setBounds(74, 58, 312, 14);
			getContentPane().add(lblauthor);
		}
		{
			JLabel lblPlaylist = new JLabel("https://www.youtube.com/watch?v=52soIAnHDvk&list=PLbEOwbQR9lqxVuDWNIrG57_JGcbIL3FWP&ab_channel=ProfessorJos%C3%A9deAssis");
			lblPlaylist.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					abrirLink("https://www.youtube.com/watch?v=52soIAnHDvk&list=PLbEOwbQR9lqxVuDWNIrG57_JGcbIL3FWP&ab_channel=ProfessorJos%C3%A9deAssis");
				}
			});
			lblPlaylist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblPlaylist.setForeground(SystemColor.textHighlight);
			lblPlaylist.setBounds(74, 83, 312, 14);
			getContentPane().add(lblPlaylist);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Projeto prático feito por AdeilsonLaureano");
			lblNewLabel_2.setBounds(74, 108, 312, 14);
			getContentPane().add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("WebService");
			lblNewLabel_3.setBounds(74, 133, 69, 14);
			getContentPane().add(lblNewLabel_3);
		}
		{
			JLabel lblWebService = new JLabel("republicavirtual.com.br");
			lblWebService.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					abrirLink("http://republicavirtual.com.br");
				}
			});
			lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblWebService.setForeground(SystemColor.textHighlight);
			lblWebService.setBounds(153, 133, 160, 14);
			getContentPane().add(lblWebService);
		}
		
		JLabel lblGitHub = new JLabel("");
		lblGitHub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirLink("https://github.com/adeilsonLaureano");
			}
		});
		lblGitHub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		lblGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblGitHub.setBorder(null);
		lblGitHub.setBackground(SystemColor.control);
		lblGitHub.setBounds(125, 183, 34, 34);
		getContentPane().add(lblGitHub);
		
		JLabel lblLinkedin = new JLabel("");
		lblLinkedin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirLink("https://www.linkedin.com/in/adeilson-laureano-77b392258/");
			}
		});
		lblLinkedin.setIcon(new ImageIcon(Sobre.class.getResource("/img/linkedin.png")));
		lblLinkedin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLinkedin.setBorder(null);
		lblLinkedin.setBackground(SystemColor.control);
		lblLinkedin.setBounds(179, 183, 34, 34);
		getContentPane().add(lblLinkedin);
		{
			JLabel lblNewLabel_4 = new JLabel("Framework");
			lblNewLabel_4.setBounds(74, 158, 69, 14);
			getContentPane().add(lblNewLabel_4);
		}
		{
			JLabel lblFramework = new JLabel("https://dom4j.github.io/");
			lblFramework.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					abrirLink("https://dom4j.github.io/");
				}
			});
			lblFramework.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblFramework.setForeground(SystemColor.textHighlight);
			lblFramework.setBounds(153, 158, 160, 14);
			getContentPane().add(lblFramework);
		}
	} // fim do construtor 
	
	private void abrirLink(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("erro: "+e);
		}
	}
}
