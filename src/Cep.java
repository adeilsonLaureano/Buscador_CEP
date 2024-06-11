import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.XMLConstants;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUf;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cep() {
		setTitle("Buscador de CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEP: ");
		lblNewLabel.setBounds(46, 33, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEndereco = new JLabel("Endereco:");
		lblEndereco.setBounds(46, 92, 84, 14);
		contentPane.add(lblEndereco);
		
		JLabel lblNewLabel_1_1 = new JLabel("Barro:");
		lblNewLabel_1_1.setBounds(46, 120, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cidade:");
		lblNewLabel_1_1_1.setBounds(46, 148, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtCep = new JTextField();
		txtCep.setBounds(75, 30, 86, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(116, 89, 278, 20);
		contentPane.add(txtEndereco);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(115, 117, 279, 20);
		contentPane.add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(115, 145, 162, 20);
		contentPane.add(txtCidade);
		
		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboUf.setBounds(327, 144, 67, 22);
		contentPane.add(cboUf);
		
		JLabel lblNewLabel_1 = new JLabel("UF:");
		lblNewLabel_1.setBounds(287, 148, 30, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnLimpa = new JButton("Limpar");
		btnLimpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpa.setBounds(46, 199, 89, 23);
		contentPane.add(btnLimpa);
		
		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor digitar o CEP!", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtCep.requestFocus();
				}
				else {
					buscarCep();
				}
			}
		});
		btnCep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCep.setBounds(197, 29, 89, 23);
		contentPane.add(btnCep);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Sobre dialogSobre = new Sobre();
				dialogSobre.setVisible(true);
				
			}
		});
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setIcon(new ImageIcon(Cep.class.getResource("/img/info.png")));
		lblNewLabel_2.setBorder(null);
		lblNewLabel_2.setBackground(SystemColor.control);
		lblNewLabel_2.setBounds(376, 11, 48, 48);
		contentPane.add(lblNewLabel_2);
		
		// uso da biblioteca para a validacao do campo txtCep
		RestrictedTextField valida = new RestrictedTextField(txtCep);
		
		lblStatus = new JLabel("");
		lblStatus.setBackground(SystemColor.control);
		lblStatus.setBorder(null);
		lblStatus.setBounds(167, 30, 20, 20);
		contentPane.add(lblStatus);
		valida.setOnlyNums(true);
		valida.setLimit(8);
		
	} // fim do construtor
	
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep="+cep+"&formato=xml");
	        SAXReader reader = new SAXReader();
	        Document document = reader.read(url);
			Element root = document.getRootElement();
			
		    // iterate through child elements of root
		    for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
		        Element element = it.next();
		        if(element.getQualifiedName().equals("cidade")) {
		        	txtCidade.setText(element.getText());
		        }
		        if(element.getQualifiedName().equals("bairro")) {
		        	txtBairro.setText(element.getText());
		        }
		        if(element.getQualifiedName().equals("uf")) {
		        	cboUf.setSelectedItem(element.getText());
		        }
		        if(element.getQualifiedName().equals("tipo_logradouro")) {
		        	tipoLogradouro = element.getText();
		        }
		        if(element.getQualifiedName().equals("logradouro")) {
		        	logradouro = element.getText();
		        }
		        if(element.getQualifiedName().equals("resultado")) {
		        	resultado = element.getText();
		        	if(resultado.equals("1")) {
		        		lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
		        		
		        	}
		        	else {
		        		JOptionPane.showMessageDialog(null, "CEP não encontrado!", "ERROR", JOptionPane.ERROR_MESSAGE);
						txtCep.requestFocus();		        	}
		        }
		    }
		    // setar o campo endereco
		    txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // fim do construtor
	
	private void limpar() {
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
	}
	
	
}
