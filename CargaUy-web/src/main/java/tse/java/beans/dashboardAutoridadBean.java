package tse.java.beans;

import org.primefaces.model.*;
import org.primefaces.model.chart.PieChartModel;
import tse.java.dto.DashboardPieChartDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.RubroDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.persistance.ITipoCargaDAO;
import tse.java.service.IEmpresasService;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IRubrosService;
import tse.java.service.IVehiculosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named("dashboardBean")
@ViewScoped
public class dashboardAutoridadBean implements Serializable {

    private DashboardModel model;

    private int cant_vehiculos, cant_empresas, cant_guias;
    private PieChartModel graficoTipoCarga;

    private PieChartModel graficoRubro;
    private List<DashboardPieChartDTO> listadoCargasNew = new ArrayList<DashboardPieChartDTO>();

    private List<DashboardPieChartDTO> listadoRubros = new ArrayList<DashboardPieChartDTO>();

    private List<GuiaDeViajeDTO> guias = new ArrayList<GuiaDeViajeDTO>();

    private List<String> rubros = new ArrayList<String>();

    private List<String> tiposCarga = new ArrayList<String>();
    private List<FilterMeta> filterBy;


    @EJB
    IVehiculosService vehiculosService;

    @EJB
    IEmpresasService empresasService;

    @EJB
    IGuiaDeViajesService guiaDeViajesService;

    @EJB
    ITipoCargaDAO tipoCargaDAO;

    @EJB
    IRubrosService rubrosService;

    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        cant_vehiculos = vehiculosService.obtenerVehiculos().getListaVehiculos().size();
        cant_empresas = empresasService.obtenerEmpresas().size();
        cant_guias = guiaDeViajesService.listarGuiasDeViajes().size();
        column1.addWidget("empresas");
        column2.addWidget("vehiculos");
        column3.addWidget("guias");
        column1.addWidget("pietipo");
        column3.addWidget("pierubro");
        column1.addWidget("tablaguias");
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        guias = guiaDeViajesService.listarGuiasDeViajes();
        guias.sort(Comparator.comparing(GuiaDeViajeDTO::getNumero));
        inicializarArrayFiltros();
        crearGraficoTipoCarga();
        crearGraficoRubros();
    }

    public LocalDate convertirALocalDate(Date fecha){
        return fecha.toLocalDate();
    }

    private void inicializarArrayFiltros(){
        List<TipoCargaDTO> taux = tipoCargaDAO.listarTipoCarga();
        for(TipoCargaDTO t:taux)
            tiposCarga.add(t.getNombre());
        List<RubroDTO> raux = rubrosService.listarRubros();
        for(RubroDTO r:raux)
            rubros.add(r.getNombre());
    }

    private void crearGraficoTipoCarga(){
        graficoTipoCarga = new PieChartModel();

        for(String t:tiposCarga){
            DashboardPieChartDTO aux = new DashboardPieChartDTO(t, 0);
            listadoCargasNew.add(aux);
        }

        for(GuiaDeViajeDTO g:guias){
            buscarEnListado(g.getTipoCarga());
        }

        for(DashboardPieChartDTO lp:listadoCargasNew){
            graficoTipoCarga.set(lp.getNombreItem(),lp.getCantidad());
        }

        graficoTipoCarga.setTitle("Cantidad de guias por tipo de carga");
        graficoTipoCarga.setLegendPosition("w");
        graficoTipoCarga.setShadow(false);

    }

    private void crearGraficoRubros(){
        graficoRubro = new PieChartModel();

        for(String r:rubros){
            DashboardPieChartDTO aux = new DashboardPieChartDTO(r, 0);
            listadoRubros.add(aux);
        }

        for(GuiaDeViajeDTO g:guias){
            buscarEnListadoRubro(g.getRubroCliente());
        }

        for(DashboardPieChartDTO lp:listadoRubros){
            graficoRubro.set(lp.getNombreItem(),lp.getCantidad());
        }

        graficoRubro.setTitle("Cantidad de guias por rubro");
        graficoRubro.setLegendPosition("w");
        graficoRubro.setShadow(false);
    }

    private void buscarEnListado(String tipo){
        for(DashboardPieChartDTO l:listadoCargasNew){
            if(l.getNombreItem().equals(tipo)){
                int num = l.getCantidad();
                num+=1;
                l.setCantidad(num);
            }
        }
    }

    private void buscarEnListadoRubro(String tipo){
        for(DashboardPieChartDTO l:listadoRubros){
            if(l.getNombreItem().equals(tipo)){
                int num = l.getCantidad();
                num+=1;
                l.setCantidad(num);
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

    public PieChartModel getGraficoRubro() {
        return graficoRubro;
    }

    public void setGraficoRubro(PieChartModel graficoRubro) {
        this.graficoRubro = graficoRubro;
    }

    public List<GuiaDeViajeDTO> getGuias() {
        return guias;
    }

    public void setGuias(List<GuiaDeViajeDTO> guias) {
        this.guias = guias;
    }

    public List<String> getRubros() {
        return rubros;
    }

    public void setRubros(List<String> rubros) {
        this.rubros = rubros;
    }

    public List<String> getTiposCarga() {
        return tiposCarga;
    }

    public void setTiposCarga(List<String> tiposCarga) {
        this.tiposCarga = tiposCarga;
    }
}
