package primary;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel name = new JLabel("2023년 월급 실수령액 계산기");
	private JLabel[] tax = new JLabel[9];
	private JTextField[] output = new JTextField[9];
	private String[] taxName = {"국민연금", "건강보험", "고용보험", "장기요양보험", "소득세", "지방세", "세전 수령액", "세금", "세후 수령액", "연봉 실수령액"};
	private JLabel lbInput = new JLabel("월급을 입력하세요 ");
	private JTextField input = new JTextField(15);
	private JLabel lbInfo = new JLabel("(미혼/부양가족 없음 기준)");
	private JButton cal = new JButton("계산하기");
	private JLabel[] caution = new JLabel[4];
	private String[] cautioned = {"<!>", "모든 금액은 소숫점 첫째자리 버림", "실제 징수금액과 다를 수 있습니다"};
	private JPanel main = new JPanel();
	private Process process = new Process();
	
	public Window() {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");			
		} catch(Exception e) {}
		
		for(int i=0; i<9; i++) {
			tax[i] = new JLabel(taxName[i]);
			tax[i].setFont(new Font("Gulim", Font.PLAIN, 12));
			output[i] = new JTextField(15);
			output[i].setEditable(false);
			output[i].setFont(new Font("Gulim", Font.PLAIN, 15));
		}		
		for(int i=0; i<3; i++) {
			caution[i] = new JLabel(cautioned[i]);
			caution[i].setFont(new Font("Gulim", Font.PLAIN, 12));
		}
		
		name.setFont(new Font("Gulim", Font.BOLD, 22));
		lbInput.setFont(new Font("Gulim", Font.PLAIN, 12));
		lbInfo.setFont(new Font("Gulim", Font.PLAIN, 12));		
		cal.setFont(new Font("Gulim", Font.PLAIN, 12));
		input.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		output[7].setBackground(Color.lightGray);
		output[8].setBackground(Color.lightGray);
		
		main.setLayout(new GridBagLayout());
		
		main.add(name,	new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		main.add(lbInput,	new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(25, 0, 5, 5), 0, 0));
		main.add(input,		new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(25, 0, 5, 5), 0, 0));
		
		main.add(lbInfo,	new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		main.add(cal,		new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 35, 5), 0, 0));
		
		for(int i=0; i<6; i++) {
			main.add(tax[i],	new GridBagConstraints(0, i+5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
			main.add(output[i], new GridBagConstraints(1, i+5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		}
		
		main.add(tax[6],	new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(25, 2, 0, 2), 0, 0));
		main.add(output[6], new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(25, 2, 0, 2), 0, 0));
		main.add(tax[7],	new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 0, 2), 0, 0));
		main.add(output[7], new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 2, 0, 2), 0, 0));
		main.add(tax[8],	new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 25, 2), 0, 0));
		main.add(output[8], new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 2, 25, 2), 0, 0));
		main.add(tax[8],	new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 25, 2), 0, 0));
		main.add(output[8], new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 2, 25, 2), 0, 0));
		
		for(int i=0; i<3; i++) {
			main.add(caution[i], new GridBagConstraints(0, i+14, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		cal.addActionListener(this);
		input.addActionListener(this);
		
		add(main, "Center");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();		
		setTitle("2023년 월급 실수령액 계산기");
		setBounds(screenSize.width/2-190, screenSize.height/2-335, 380, 670);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == cal || e.getSource() == input) {
			try {
				process.start(Double.parseDouble(input.getText()), Integer.parseInt(input.getText()));
				
				output[0].setText(process.getGukmin());
				output[1].setText(process.getGungang());
				output[2].setText(process.getGoyong());
				output[3].setText(process.getJanggi());
				output[4].setText(process.getGabgeun());
				output[5].setText(process.getJumin());
				output[6].setText(process.getBeforeTax());
				output[7].setText(process.getTax());
				output[8].setText(process.getAfterTax());
				output[8].setText(process.getAfterTax());
			} catch(Exception ee) {
				JOptionPane.showMessageDialog(this, "월급여액을 정확히 입력해주세요.");
			}			
		}		
	}	
}
