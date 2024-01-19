package controllers;

import java.util.HashMap;

import conexion.ConexionBDD;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorDeLanzador {

	@FXML
	private TextField txtFldNumPac;

	@FXML
	private TextField txtFldNomPac;

	@FXML
	private TextField txtFldDirPac;

	@FXML
	private TextField txtFldCodMed;

	@FXML
	private TextField txtFldNomMed;

	@FXML
	private TextField txtFldEspMed;

	@FXML
	private TextArea txtAreTratamiento;

	@FXML
	private Button btnInforme;

	@FXML
	private Button btnLimpiar;

	@FXML
	private Button btnSalir;

	@FXML
	void Limpiar(ActionEvent event) {
		txtFldNumPac.setText("");
		txtFldNomPac.setText("");
		txtFldDirPac.setText("");
		txtFldCodMed.setText("");
		txtFldNomMed.setText("");
		txtFldEspMed.setText("");
		txtAreTratamiento.setText("");
	}

	@FXML
	void generarInformer(ActionEvent event) {

		if (txtFldNumPac.getText().isEmpty() || !txtFldNumPac.getText().matches("[0-9]*")
				|| txtFldNomPac.getText().isEmpty() || txtFldDirPac.getText().isEmpty()
				|| txtFldCodMed.getText().isEmpty() || !txtFldCodMed.getText().matches("[0-9]*")
				|| txtFldNomMed.getText().isEmpty() || txtFldEspMed.getText().isEmpty()
				|| txtAreTratamiento.getText().isEmpty()) {
			
			Alert alertWindows = new Alert(Alert.AlertType.ERROR);
			alertWindows.setHeaderText(null);
			String mensaje = "";
			
			if (txtFldNumPac.getText().isEmpty()) {
				mensaje += "Este campo es obligatorio" + "\n";
			}
			if (!txtFldNumPac.getText().matches("[0-9]*")) {
				mensaje += "Este campo debe ser numerico" + "\n";
			}
			if (txtFldNomPac.getText().isEmpty()) {
				mensaje += "Este campo es obligatorio" + "\n";
			}
			if (txtFldDirPac.getText().isEmpty()) {
				mensaje += "Este campo es obligatorio" + "\n";
			}
			if (txtFldCodMed.getText().isEmpty()) {
				mensaje += "Este campo es obligatorio" + "\n";
			}
			if (!txtFldCodMed.getText().matches("[0-9]*")) {
				mensaje += "Este campo debe ser numerico" + "\n";
			}
			if (txtFldNomMed.getText().isEmpty()) {
				mensaje += "Este campo es obligatorio" + "\n";
			}
			if (txtFldEspMed.getText().isEmpty()) {
				mensaje += "Este campo es obligatorio" + "\n";
			}
			if (txtAreTratamiento.getText().isEmpty()) {
				mensaje += "Este campo es obligatorio" + "\n";
			}
			
			alertWindows.setContentText(mensaje);
			alertWindows.showAndWait();
		} else {
			try {
				HashMap<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("nomMedico", txtFldNomMed.getText().toString());
				parameters.put("espMedico", txtFldEspMed.getText().toString());
				parameters.put("numMedico", txtFldCodMed.getText().toString());
				parameters.put("nomPaciente", txtFldNomPac.getText().toString());
				parameters.put("dirPaciente", txtFldDirPac.getText().toString());
				parameters.put("numPaciente", txtFldNumPac.getText().toString());
				parameters.put("tratamiento", txtAreTratamiento.getText().toString());

				JasperReport report = (JasperReport) JRLoader
						.loadObject(getClass().getResource("informe_medico.jasper"));
				JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
				JasperViewer viewer = new JasperViewer(jprint, false);
				viewer.setVisible(true);
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Ha ocurrido un error");
				alert.showAndWait();
				e.printStackTrace();
			}
		}
	}

	@FXML
	void salir(ActionEvent event) {
		Platform.exit();
	}

}
