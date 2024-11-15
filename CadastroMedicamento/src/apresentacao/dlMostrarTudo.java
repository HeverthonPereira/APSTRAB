package apresentacao;

import Negocio.Medicamento;
import persistencia.controlaMedicamento;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class dlMostrarTudo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList list1;
    private JButton removerItemButton;

    controlaMedicamento cm;
    DefaultListModel model = new DefaultListModel();
    public dlMostrarTudo(controlaMedicamento cm) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.cm = cm;
        exibeInformacoes();
    }
        private void exibeInformacoes() {
            ArrayList<Medicamento> medicamentos = cm.mostrarMedicamentos();

            for (int i =0; i<medicamentos.size(); i++){
                model.addElement(medicamentos.get(i));
            }


            list1.setModel(model);
        }
        public dlMostrarTudo() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
                list1.setModel(model);
            }
        });
            buttonCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            removerItemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Medicamento> medicamentos = cm.mostrarMedicamentos();

                    int id = list1.getSelectedIndex();
                    medicamentos.remove(id);
                    model.remove(list1.getSelectedIndex()); //retorna a posição do item selecionado.
                    list1.setModel(model);
                }
            });
        }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        dlMostrarTudo dialog = new dlMostrarTudo();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
