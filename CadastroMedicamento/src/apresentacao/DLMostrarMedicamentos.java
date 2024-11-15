package apresentacao;

import Negocio.Medicamento;
import persistencia.controlaMedicamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class DLMostrarMedicamentos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    controlaMedicamento cm;
    DefaultTableModel modeloTabela;

    public DLMostrarMedicamentos(controlaMedicamento cm) {
        String[] colunas = {"ID", "Nome", "Data de Fabricaçao", "Data de Validade", "Principio Ativo", "Dosagem", "Indicações"};

        modeloTabela = new DefaultTableModel(colunas, 0);

        for(Medicamento medicamentos : cm.mostrarMedicamentos()){
            Object[] dadosMedicamentos = { medicamentos.getID(),medicamentos.getNome(),medicamentos.getDataFabricacao().format(formatter),medicamentos.getDataValidade().format(formatter),medicamentos.getPrincipioAtivo(),medicamentos.getDosagem(),medicamentos.getIndicacao()};

            modeloTabela.addRow(dadosMedicamentos);
        }

        JTable tabelaMedicamentos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaMedicamentos);
        JOptionPane.showMessageDialog(null,
            scrollPane, "Lista de Medicamentos",
            JOptionPane.INFORMATION_MESSAGE);
    }
    public DLMostrarMedicamentos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        DLMostrarMedicamentos dialog = new DLMostrarMedicamentos();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
