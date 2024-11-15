package apresentacao;

import Negocio.Medicamento;
import persistencia.controlaMedicamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class principal extends JFrame{
    private JTextField TMedicamento;
    private JTextField TDataFabricacao;
    private JButton btnSalvar;
    private JButton btnLimpar;
    private JTextArea tResultado;
    private JPanel MainPanel;
    private JTextField TPrincipioAtivo;
    private JTextField TDataValidade;
    private JButton mostrarTudoButton;
    private JButton btable;
    private JTextField TDosagem;
    private JTextField TIndicacao;
    private JButton EDITARButton;


    controlaMedicamento cm = new controlaMedicamento();
//    DefaultListModel model = new DefaultListModel();

    public void limparCampos(){
        TMedicamento.setText("");
        TPrincipioAtivo.setText("");
        TDataFabricacao.setText("");
        TDataValidade.setText("");
        TDosagem.setText("");
        TIndicacao.setText("");
        TMedicamento.requestFocus();
    }
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public principal(){
        setContentPane(MainPanel);
        setTitle("Cadastro de Medicamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700,400);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 if ((!"".equals(TMedicamento.getText())) && (!"".equals(TPrincipioAtivo.getText()))&& (!"".equals(TDataFabricacao.getText())) && (!"".equals(TDataValidade.getText())) && (!"".equals(TDosagem.getText())) && (!"".equals(TIndicacao.getText()))) {
                     Medicamento medicamento = new Medicamento();
                     int id = medicamento.getProximoIdMedicamento();
                     medicamento.setID(id);

                     medicamento.setNome(TMedicamento.getText());
                     medicamento.setPrincipioAtivo(TPrincipioAtivo.getText());
                     medicamento.setDataFabricacao(LocalDate.parse(TDataFabricacao.getText(),formatter) );
                     medicamento.setDataValidade(LocalDate.parse(TDataValidade.getText(),formatter));
                     medicamento.setDosagem(TDosagem.getText());
                     medicamento.setIndicacao(TIndicacao.getText());

                     if(cm.addMedicamento(medicamento)){
                         JOptionPane.showMessageDialog(null, "Medicamento cadastrado com sucesso!");
                         limparCampos();


                         /*model.addElement(String.valueOf(cm.mostrarMedicamentos()));

                         Jlist1.setModel(model);*/
                     }else {
                         JOptionPane.showMessageDialog(null, "Erro ao cadastrar medicamento!");
                         limparCampos();
                     }

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
        mostrarTudoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlMostrarTudo dlg =new dlMostrarTudo(cm);
                dlg.setVisible(true);
            }
        });
        btable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            DLMostrarMedicamentos dlg = new DLMostrarMedicamentos(cm);
            dlg.setVisible(true);
            }
        });
        EDITARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlterarMedicamento dlg = new AlterarMedicamento(cm);
                dlg.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {


    }
}
