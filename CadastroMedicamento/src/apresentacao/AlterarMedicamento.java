package apresentacao;

import Negocio.Medicamento;
import persistencia.controlaMedicamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AlterarMedicamento extends JFrame {
    private JTextField TMedicamento;
    private JTextField TDataFabricacao;
    private JButton btnAlterar;
    private JButton btnLimpar;
    private JTextArea tResultado;
    private JPanel MainPanel;
    private JTextField TPrincipioAtivo;
    private JTextField TDataValidade;
    private JButton mostrarTudoButton;
    private JButton btable;
    private JTextField TDosagem;
    private JTextField TIndicacao;
    private JButton btPesquisa;
    private JTextField txtPesquisa;
    private JButton excluirButton;
    private Optional<Medicamento> medicamento;

//    controlaMedicamento cm = new controlaMedicamento();
//    DefaultListModel model = new DefaultListModel();


    public void limparCampos() {
        TMedicamento.setText("");
        TPrincipioAtivo.setText("");
        TDataFabricacao.setText("");
        TDataValidade.setText("");
        TDosagem.setText("");
        TIndicacao.setText("");
        TMedicamento.requestFocus();
        txtPesquisa.setText("");
    }

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AlterarMedicamento(controlaMedicamento cm) {
        setContentPane(MainPanel);
        setTitle("Cadastro de Medicamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 400);

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((!"".equals(TMedicamento.getText())) && (!"".equals(TPrincipioAtivo.getText())) && (!"".equals(TDataFabricacao.getText())) && (!"".equals(TDataValidade.getText())) && (!"".equals(TDosagem.getText())) && (!"".equals(TIndicacao.getText()))) {
                    medicamento.ifPresentOrElse(medicamento1 -> {

                        medicamento1.setNome(TMedicamento.getText());
                        medicamento1.setPrincipioAtivo(TPrincipioAtivo.getText());
                        medicamento1.setDataFabricacao(LocalDate.parse(TDataFabricacao.getText(), formatter));
                        medicamento1.setDataValidade(LocalDate.parse(TDataValidade.getText(), formatter));
                        medicamento1.setDosagem(TDosagem.getText());
                        medicamento1.setIndicacao(TIndicacao.getText());
                        JOptionPane.showMessageDialog(null, "Alterado com sucesso!!");
                        limparCampos();
                    }, () -> {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar medicamento!");
                        limparCampos();
                    });

                         /*model.addElement(String.valueOf(cm.mostrarMedicamentos()));

                         Jlist1.setModel(model);*/


                } else {
                    JOptionPane.showMessageDialog(null, "Prenchimento Obrigatório dos campos!");
                    limparCampos();
                }
            }
        });
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        btPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtPesquisa.getText());
                medicamento = cm.recuperarMedicamento(id);
                medicamento.ifPresent(medicamento1 -> {
                    TMedicamento.setText(medicamento1.getNome());
                    TIndicacao.setText(medicamento1.getIndicacao());
                    TDosagem.setText(medicamento1.getDosagem());
                    TDataValidade.setText(medicamento1.getDataValidade().format(formatter));
                    TDataFabricacao.setText(medicamento1.getDataFabricacao().format(formatter));
                    TPrincipioAtivo.setText(medicamento1.getPrincipioAtivo());


                });
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtPesquisa.getText());
                for (int i = 0; i < cm.medicamentos.size(); i++) {
                    Medicamento med = cm.medicamentos.get(i);
                    if (med.getID() == id) {
                        cm.medicamentos.remove(i);

                        JOptionPane.showMessageDialog(null, "Medicamento excluido com sucesso!");
                        limparCampos();
                        break;
                    }
                }
            }

        });
    }
}