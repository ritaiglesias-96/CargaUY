package tse.java.beans;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartModel;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.ListadoPieDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.persistance.ITipoCargaDAO;
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
import java.util.ArrayList;
import java.util.List;

@Named("dashboardBean")
@ViewScoped
public class dashboardAutoridadBean implements Serializable {

    private DashboardModel model;

    private int cant_vehiculos, cant_empresas, cant_guias;

    private BarChartModel graficoGuias;

    private PieChartModel graficoTipoCarga;


    @EJB
    IVehiculosService vehiculosService;

    @EJB
    IEmpresasService empresasService;

    @EJB
    IGuiaDeViajesService guiaDeViajesService;

    @EJB
    ITipoCargaDAO tipoCargaDAO;

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
        column1.addWidget("graficoguias");
        column2.addWidget("vehiculos");
        column3.addWidget("guias");
        column3.addWidget("pietipo");
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        crearGraficoGuias();
        crearGraficoTipoCarga();
    }


    private void crearGraficoGuias(){
        graficoGuias = new BarChartModel();
    }

    private void crearGraficoTipoCarga(){
        graficoTipoCarga = new PieChartModel();
        List<TipoCargaDTO> listadoCargas = tipoCargaDAO.listarTipoCarga();
        List<ListadoPieDTO> listadoCargasNew = new ArrayList<ListadoPieDTO>();
        for(TipoCargaDTO t:listadoCargas){
            ListadoPieDTO aux = new ListadoPieDTO(t.getNombre(), 0);
            listadoCargasNew.add(aux);
        }
        List<Integer> listadoCargasCant = new ArrayList<Integer>();
        for(GuiaDeViajeDTO g:guiaDeViajesService.listarGuiasDeViajes()){
            buscarEnListado(listadoCargasNew, g.getTipoCarga());
        }
    }

    private void buscarEnListado(List<ListadoPieDTO> list, String tipo){
        for(ListadoPieDTO l:list){
            if(l.getNombreTipoCarga().equals(tipo)){
                int num = l.getCantidad();
                l.setCantidad(num++);
            }
        }
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

    public PieChartModel getGraficoTipoCarga() {
        return graficoTipoCarga;
    }

    public void setGraficoTipoCarga(PieChartModel graficoTipoCarga) {
        this.graficoTipoCarga = graficoTipoCarga;
    }

    public BarChartModel getGraficoGuias() {
        return graficoGuias;
    }

    public void setGraficoGuias(BarChartModel graficoGuias) {
        this.graficoGuias = graficoGuias;
    }
}
