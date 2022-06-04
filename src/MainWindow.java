import com.alibaba.fastjson2.JSON;
import wzy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class MainWindow extends JFrame {
    private Container container;
    private JLabel label;
    private JButton button1, button2, button3, button4, button5;
    private JList<Person> list;
    private JTextField textField;

    private static Vector<Person> persons;

    private static Vector<Person> result;


    public MainWindow() {
        super("电子名片管理器");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                File f = new File("data.json");
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException err) {
                        throw new RuntimeException(err);
                    }
                }
                try {
                    System.out.println(JSON.toJSONString(persons));
                    BufferedWriter w = new BufferedWriter(new FileWriter(f,StandardCharsets.UTF_8));
                    w.write(JSON.toJSONString(persons));
                    w.flush();
                    w.close();
                } catch (IOException err) {
                    throw new RuntimeException(err);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        ButtonActionHandle handle = new ButtonActionHandle();
        container = getContentPane();
        container.setLayout(null);
        container.setBounds(0, 0, 800, 600);

        label = new JLabel();
        label.setText("查询内容:");
        label.setBounds(79, 51, 90, 58);
        container.add(label);

        textField = new JTextField();
        textField.setBounds(189, 51, 430, 58);
        container.add(textField);

        button1 = new JButton("查询");
        button1.setBounds(646, 51, 90, 58);
        button1.addActionListener(handle);
        container.add(button1);

        button2 = new JButton("详情");
        button2.setBounds(646, 130, 90, 58);
        button2.addActionListener(handle);
        container.add(button2);

        button3 = new JButton("修改");
        button3.setBounds(646, 209, 90, 58);
        button3.addActionListener(handle);
        container.add(button3);

        button4 = new JButton("删除");
        button4.setBounds(646, 288, 90, 58);
        button4.addActionListener(handle);
        container.add(button4);

        button5 = new JButton("新建");
        button5.setBounds(646, 367, 90, 58);
        button5.addActionListener(handle);
        container.add(button5);

        list = new JList<Person>();
        list.setBounds(78, 130, 541, 420);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane();    //创建滚动面板
        scrollPane.setBounds(78, 130, 541, 420);
        container.add(scrollPane);
        scrollPane.setViewportView(list);

        list.setListData(persons);    //为列表填充数据

        this.setVisible(true);
    }


    public static void main(String[] args) {
        File f = new File("data.json");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        persons = new Vector<>();
        result = new Vector<>();
        try {
            BufferedReader r = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
            persons.addAll(JSON.parseArray(r.readLine(), Person.class));
            result.addAll(persons);
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
        MainWindow window = new MainWindow();
        System.out.println("中文");
    }

    private class ButtonActionHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                //查询
                System.out.println("textField = " + textField.getText());
                result.clear();
                for (Person person : persons) {
                    if (person.getName() != null && person.getName().contains(textField.getText())) {
                        result.add(person);
                    } else if (person.getPhoneNumber() != null && person.getPhoneNumber().contains(textField.getText())) {
                        result.add(person);
                    } else if (person.getTel() != null && person.getTel().contains(textField.getText())) {
                        result.add(person);
                    } else if (person.getOffice() != null && person.getOffice().contains(textField.getText())) {
                        result.add(person);
                    } else if (person.getEmail() != null && person.getEmail().contains(textField.getText())) {
                        result.add(person);
                    } else if (person.getCompany() != null && person.getCompany().contains(textField.getText())) {
                        result.add(person);
                    }
                }
                if (textField.getText().equals("男")) {
                    for (Person person : persons) {
                        if (person.getGender() != null && person.getGender().equals(Gender.male)) {
                            result.add(person);
                        }
                    }
                } else if (textField.getText().equals("女")) {
                    for (Person person : persons) {
                        if (person.getGender() != null && person.getGender().equals(Gender.female)) {
                            result.add(person);
                        }
                    }
                }
                list.setListData(result);
            } else if (e.getSource() == button2) {
                //详情
                PersonDetailWindow w = new PersonDetailWindow(list.getSelectedValue());
            } else if (e.getSource() == button3) {
                //修改
                PersonModifyWindow w = new PersonModifyWindow(list.getSelectedValue());
                list.setListData(result);
            } else if (e.getSource() == button4) {
                //删除
                Person toBeRemoved = list.getSelectedValue();
                persons.remove(toBeRemoved);
                result.remove(toBeRemoved);
                list.setListData(result);
            } else if (e.getSource() == button5) {
                //新建
                PersonCreateWindow w = new PersonCreateWindow();
                persons.addElement(w.getPerson());
                result.addElement(w.getPerson());
                list.setListData(result);
            }
        }
    }
}
