package persistencia;

import Negocio.Medicamento;

import java.util.ArrayList;
import java.util.Optional;

public class controlaMedicamento {
    public ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();

    public boolean addMedicamento(Medicamento m){
        if(m != null){
            medicamentos.add(m);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Medicamento> mostrarMedicamentos() {
        return medicamentos;
    }

    @Override
    public String toString() {
        return "" + medicamentos;
    }

    public Optional<Medicamento>  recuperarMedicamento(int id) {
        return medicamentos.stream().filter(med -> med.getID() == id).findFirst();
    }
}


