import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Program {

    private DefaultListModel<Book> model;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel booksPanel;
    private JPanel exportPanel;
    private JTextField titleTextField;
    private JTextField categoryTextField;
    private JLabel titleLabel;
    private JLabel categoryLabel;
    private JButton submitButton;
    private JPanel createPanel;
    private JButton exportButton;
    private JPanel updatePanel;
    private JLabel updateTitleLabel;
    private JTextField updateTitleTextField;
    private JLabel updateCategoryLabel;
    private JTextField updateCategoryTextField;
    private JButton updateButton;
    private JList bookList;
    private JList bookList2;
    private JButton Books;
    private JButton csvButton;
    private JButton tsvButton;

    public Program() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = titleTextField.getText();
                String category = categoryTextField.getText();
                model.addElement(new Book(name, category));
            }
        });
        bookList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                    if (bookList.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        updateButton.setEnabled(false);

                    } else {
                        //Selection, enable the fire button.
                        updateButton.setEnabled(true);
                        updateTitleTextField.setText(((Book)bookList.getSelectedValue()).getTitle());
                        updateCategoryTextField.setText(((Book)bookList.getSelectedValue()).getCat());

                    }
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.get(bookList.getSelectedIndex()).setTitle(updateTitleTextField.getText());
                bookList.repaint();
                bookList.revalidate();
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                contentPanel.removeAll();
                contentPanel.repaint();
                contentPanel.revalidate();

                contentPanel.add(exportPanel);
                contentPanel.repaint();
                contentPanel.revalidate();
            }
        });
        Books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                contentPanel.removeAll();
                contentPanel.repaint();
                contentPanel.revalidate();

                contentPanel.add(booksPanel);
                contentPanel.repaint();
                contentPanel.revalidate();
            }
        });
        bookList2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                    if (bookList2.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        updateButton.setEnabled(false);

                    } else {
                        //Selection, enable the fire button.
                        updateButton.setEnabled(true);
                    }
                }
            }
        });
        csvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    PrintWriter writer = new PrintWriter(new FileOutputStream("Books_CSV.csv"));
                    for (int i : bookList2.getSelectedIndices()){
                        writer.println(model.get(i).getTitle() + "," + model.get(i).getCat());
                    }
                    writer.flush();
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        tsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    PrintWriter writer = new PrintWriter(new FileOutputStream("Books_TSV.csv"));
                    for (int i : bookList2.getSelectedIndices()){
                        writer.println(model.get(i).getTitle() + "\t" + model.get(i).getCat());
                    }
                    writer.flush();
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String args[]){
        //test
        JFrame frame = new JFrame();
        frame.setContentPane(new Program().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

    private void createUIComponents() {
        bookList = new JList();
        bookList2 = new JList();
        model = new DefaultListModel<>();
        model.addElement(new Book("Beginner Physics", "Science"));
        model.addElement(new Book("Game of Thrones", "Sexual education"));
        model.addElement(new Book("The Bible", "Fantasy"));
        bookList.setModel(model);
        bookList2.setModel(model);

    }
}
