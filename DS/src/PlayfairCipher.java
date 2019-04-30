import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayfairCipher extends JFrame {
	
	
	private char[][] key =
		{
			{'G', 'R', 'U', 'P', 'I'},
			{'E', 'S', 'T', 'A', 'B'},
			{'C', 'D', 'F', 'H', 'K'},
			{'L', 'M', 'N', 'O', 'Q'},
			{'X', 'W', 'V', 'Y', 'Z'}
		};
	
	private String Encrypt(String plaintext, char[][] key)
	 {
			plaintext = plaintext.replace(" ", "");
			plaintext = plaintext.replace("J", "I");
			for (int i = 0; i < plaintext.length() - 1; i++)
			{
				if (plaintext.charAt(i) == plaintext.charAt(i + 1))
				{  
				plaintext=plaintext.substring(0,1+i)+"X"+plaintext.substring(1+i);
				}
			}
			if (plaintext.length() % 2 != 0)
			{
				plaintext += "X";
			}

			StringBuffer ciphertext = new StringBuffer(plaintext);
			for (int i = 0; i < plaintext.length(); i += 2)
			{
				char karakter1 = plaintext.charAt(i);
				char karakter2 = plaintext.charAt(i + 1);

				int karakteri1r = 0, karakteri1k = 0;
				int karakteri2r = 0, karakteri2k = 0;

				boolean Flag = false;

				for (int r = 0; r < 5; r++)
				{
					for (int c = 0; c < 5; c++)
					{
						if (karakter1 == key[r][c])
						{
							karakteri1r = r;
							karakteri1k = c;
							Flag = true;
							break;
						}
					}
					if (Flag)
					{
						break;
					}
				}
			   Flag = false;
				for (int r = 0; r < 5; r++)
				{
					for (int c = 0; c < 5; c++)
					{
						if (karakter2 == key[r][c])
						{
							karakteri2r = r;
							karakteri2k = c;
							Flag = true;
							break;
						}
					}
					if (Flag)
					{
						break;
					}
				}

				char karakter1e, karakter2e;
				if (karakteri1r == karakteri2r)
				{
					karakter1e = key[karakteri1r][(karakteri1k + 1) % 5];
					karakter2e = key[karakteri2r][(karakteri2k + 1) % 5];
				}
				else if (karakteri1k == karakteri2k)
				{
					karakter1e = key[(karakteri1r + 1) % 5][karakteri1k];
					karakter2e = key[(karakteri2r + 1) % 5][karakteri2k];
				}
				else
				{
					karakter1e = key[karakteri1r][karakteri2k];
					karakter2e = key[karakteri2r][karakteri1k];
				}
				ciphertext.setCharAt(i, karakter1e);
				ciphertext.setCharAt(i + 1, karakter2e);
			}

			return ciphertext.toString();
	 }
	private String Decrypt(String ciphertext, char[][] key)
	{
		StringBuilder plaintext = new StringBuilder(ciphertext);
		for (int i = 0; i < ciphertext.length(); i += 2)
		{
			char karakter1 = ciphertext.charAt(i);
			char karakter2 = ciphertext.charAt(i + 1);

			int karakteri1r = 0, karakteri1k = 0;
			int karakteri2r = 0, karakteri2k = 0;

			boolean Flag = false;

			for (int r = 0; r < 5; r++)
			{
				for (int c = 0; c < 5; c++)
				{
					if (karakter1 == key[r][c])
					{
						karakteri1r = r;
						karakteri1k = c;
						Flag = true;
						break;
					}
				}
				if (Flag)
				{
					break;
				}
			}

			Flag = false;
			for (int r = 0; r < 5; r++)
			{
				for (int c = 0; c < 5; c++)
				{
					if (karakter2 == key[r][c])
					{
						karakteri2r = r;
						karakteri2k = c;
						Flag = true;
						break;
					}
				}
				if (Flag)
				{
					break;
				}
			}

			char karakter1d, karakter2d;
			if (karakteri1r == karakteri2r)
			{
				karakter1d = key[karakteri1r][(karakteri1k - 1 + 5) % 5];
				karakter2d = key[karakteri2r][(karakteri2k - 1 + 5) % 5];
			}
			else if (karakteri1k == karakteri2k)
			{
				karakter1d = key[(karakteri1r - 1 + 5) % 5][karakteri1k];
				karakter2d = key[(karakteri2r - 1 + 5) % 5][karakteri2k];
			}
			else
			{
				karakter1d = key[karakteri1r][karakteri2k];
				karakter2d = key[karakteri2r][karakteri1k];
			}
			plaintext.setCharAt(i, karakter1d);
			plaintext.setCharAt(i + 1, karakter2d);
		}

		return plaintext.toString();
	}


    
	private JPanel contentPane;
	private JTextField txtplaintexti;
	private JTextField txtciphertext;
	private JTextField txttekstidenkriptuar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayfairCipher frame = new PlayfairCipher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PlayfairCipher() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Plain teksti");
		label.setBounds(10, 11, 93, 14);
		contentPane.add(label);
		
		txtplaintexti = new JTextField();
		txtplaintexti.setColumns(10);
		txtplaintexti.setBounds(10, 29, 414, 33);
		contentPane.add(txtplaintexti);
		
		JButton button = new JButton("Enkripto");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				txtciphertext.setText(Encrypt(txtplaintexti.getText(), key));
			}
		});
		button.setBackground(new Color(173, 255, 47));
		button.setBounds(293, 65, 131, 29);
		contentPane.add(button);
		
		txtciphertext = new JTextField();
		txtciphertext.setColumns(10);
		txtciphertext.setBounds(10, 123, 414, 33);
		contentPane.add(txtciphertext);
		
		JButton button_1 = new JButton("Denkripto");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txttekstidenkriptuar.setText(Decrypt(txtciphertext.getText(), key));
			
			}
		});
		button_1.setBackground(new Color(255, 99, 71));
		button_1.setBounds(293, 160, 131, 29);
		contentPane.add(button_1);
		
		txttekstidenkriptuar = new JTextField();
		txttekstidenkriptuar.setColumns(10);
		txttekstidenkriptuar.setBounds(10, 217, 414, 33);
		contentPane.add(txttekstidenkriptuar);
		
		JLabel lblPlainTeksti = new JLabel("Teksti Dekriptuar");
		lblPlainTeksti.setBackground(new Color(245, 255, 250));
		lblPlainTeksti.setBounds(10, 203, 124, 14);
		contentPane.add(lblPlainTeksti);
		
		JLabel label_2 = new JLabel("Cipher teksti");
		label_2.setBounds(10, 110, 93, 14);
		contentPane.add(label_2);
	}

}
