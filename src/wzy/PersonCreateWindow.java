package wzy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonCreateWindow extends JFrame {

    private Person person;
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    protected JTextField t1, t2, t3, t4, t5, t6, t7, t8;

    private Container container;
    private JButton ret;

    public PersonCreateWindow() {
        super("新建");
        this.setSize(400, 600);
        container = getContentPane();
        container.setLayout(null);
        this.person = new Person();
        label1 = new JLabel("姓名:");
        label2 = new JLabel("性别:");
        label3 = new JLabel("公司:");
        label4 = new JLabel("职务:");
        label5 = new JLabel("手机:");
        label6 = new JLabel("座机:");
        label7 = new JLabel("E-mail:");
        label8 = new JLabel("QQ:");

        label1.setBounds(35, 40, 84, 37);
        label2.setBounds(35, 91, 84, 37);
        label3.setBounds(35, 142, 84, 37);
        label4.setBounds(35, 193, 84, 37);
        label5.setBounds(35, 244, 84, 37);
        label6.setBounds(35, 295, 84, 37);
        label7.setBounds(35, 346, 84, 37);
        label8.setBounds(35, 397, 84, 37);

        t1 = new JTextField(person.getName());
        t2 = new JTextField();
        t3 = new JTextField(person.getCompany());
        t4 = new JTextField(person.getOffice());
        t5 = new JTextField(person.getPhoneNumber());
        t6 = new JTextField(person.getTel());
        t7 = new JTextField(person.getEmail());
        t8 = new JTextField(person.getQQ());

        t1.setBounds(137, 40, 226, 37);
        t2.setBounds(137, 91, 226, 37);
        t3.setBounds(137, 142, 226, 37);
        t4.setBounds(137, 193, 226, 37);
        t5.setBounds(137, 244, 226, 37);
        t6.setBounds(137, 295, 226, 37);
        t7.setBounds(137, 346, 226, 37);
        t8.setBounds(137, 397, 226, 37);

        ret = new JButton("保存并返回");
        ret.setBounds(93, 474, 215, 65);
        ret.addActionListener(new RetAndSaveButtonHandle(this));
        container.add(label1);
        container.add(label2);
        container.add(label3);
        container.add(label4);
        container.add(label5);
        container.add(label6);
        container.add(label7);
        container.add(label8);

        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(t5);
        container.add(t6);
        container.add(t7);
        container.add(t8);

        container.add(ret);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public Person getPerson() {
        return person;
    }

    private class RetAndSaveButtonHandle implements ActionListener {
        private PersonCreateWindow frame;
        private Person person;

        public RetAndSaveButtonHandle(JFrame frame) {
            this.frame = (PersonCreateWindow) frame;
            this.person = this.frame.person;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            person.setName(frame.t1.getText());
            person.setGender(frame.t2.getText().equals("女") ? Gender.female : Gender.male);
            person.setCompany(frame.t3.getText());
            person.setOffice(frame.t4.getText());
            person.setPhoneNumber(frame.t5.getText());
            person.setTel(frame.t6.getText());
            person.setEmail(frame.t7.getText());
            person.setQQ(frame.t8.getText());
            frame.dispose();
        }
    }
}
