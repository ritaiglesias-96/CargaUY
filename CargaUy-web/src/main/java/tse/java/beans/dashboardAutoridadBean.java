package tse.java.beans;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import tse.java.service.IEmpresasService;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IVehiculosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("dashboardBean")
@ViewScoped
public class dashboardAutoridadBean implements Serializable {

    private DashboardModel model;

    private int cant_vehiculos, cant_empresas, cant_guias;

    @EJB
    IVehiculosService vehiculosService;

    @EJB
    IEmpresasService empresasService;

    @EJB
    IGuiaDeViajesService guiaDeViajesService;

    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        cant_vehiculos = vehiculosService.obtenerVehiculos().getListaVehiculos().size();
        cant_empresas = empresasService.obtenerEmpresas().getTotalRows();
        cant_guias = guiaDeViajesService.listarGuiasDeViajes().size();
        column1.addWidget("empresas");

        column2.addWidget("vehiculos");

        column3.addWidget("guias");
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex()
                + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed",
                "Closed panel id:'" + event.getComponent().getId() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
                "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }

    public int getCant_vehiculos() {
        return cant_vehiculos;
    }

    public void setCant_vehiculos(int cant_vehiculos) {
        this.cant_vehiculos = cant_vehiculos;
    }

    public int getCant_empresas() {
        return cant_empresas;
    }

    public void setCant_empresas(int cant_empresas) {
        this.cant_empresas = cant_empresas;
    }

    public int getCant_guias() {
        return cant_guias;
    }

    public void setCant_guias(int cant_guias) {
        this.cant_guias = cant_guias;
    }
}
