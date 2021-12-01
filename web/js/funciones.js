/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var vehiculo;
var movimiento;
var tn;

function detalleVehiculo() {
    mostrarVehiculos();
}

function mostrarVehiculos() {

    $.ajax(
            {
                type: "GET",
                async: true,
                url: "api/controlVehicular/getAllVehiculo"
            }
    ).done(function (data) {

        vehiculo = data;
        var es = "";
        var tabla = "";
        var n = 1;
        for (var i = 0; i < data.length; i++) {

            if (data[i].estatus === 1) {
                es = "CIRCULANDO";
            } else if (data[i].estatus === 0) {
                es = "NO CIRCULANDO";
            }

            tabla += "<tr>";
            tabla += "<td style='display:none' id='idVehiculo'>" + data[i].idVehiculo + "</td>";
            tabla += "<td>" + n++ + "</td>";
            tabla += "<td class='text-md-center'><button class='btn btn-info btn-sm' data-toggle='modal'\n\
                     data-target='#modalTableMovimientos' onclick='mostrarMovimientos(" + i + ")'> <i class='fas fa-table'></i></button></td>";
            tabla += "<td class='text-md-center'>" + data[i].nombre + " " + data[i].apellidoPaterno + " " + data[i].apellidoMaterno + "</td>";
            tabla += "<td>" + data[i].numeroSerie + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].placas + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].modelo + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].tenencia + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].tarjetaCirculacion + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].verificacion + "</td>";
            tabla += "<td class='text-md-center'> " + data[i].fechaVerificacion + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].refrendo + "</td>";
            tabla += "<td>" + data[i].descripcion + "</td>";
            tabla += "<td class='text-md-center'>" + es + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].descripcionEstatus + "</td>";
//            tabla += "<td class='text-md-center'><button class='btn btn-warning btn-sm' data-toggle='modal'\n\
//                     data-target='#modalFormularioModificar'  onclick='cargarModificarVehiculo(" + i + ")'> <i class='fas fa-edit'></i></button></td>";
//            
            tabla += "<td class='text-md-center'><a class='btn btn-warning btn-sm' data-toggle='modal'\n\
                     data-target='#modalFormularioModificar'  onclick='cargarModificarVehiculo(" + i + ")'> <i class='fas fa-edit'></i></a></td>";

            tabla += "</tr>";

        }

        $('#lstVehiculo').html(tabla);
    });
}

function buscarVehiculos(search) {

    search = $('#txtSearch').val();

    var data = {
        search: search
    };

    $.ajax(
            {
                type: "GET",
                async: true,
                data: data,
                url: "api/controlVehicular/searchVehiculo"
            }
    ).done(function (data) {

        vehiculo = data;
        var es = "";
        var tabla = "";
        var n = 1;
        for (var i = 0; i < data.length; i++) {

            if (data[i].estatus === 1) {
                es = "CIRCULANDO";
            } else if (data[i].estatus === 0) {
                es = "NO CIRCULANDO";
            }

            tabla += "<tr>";
            tabla += "<td style='display:none' id='idVehiculo'>" + data[i].idVehiculo + "</td>";
            tabla += "<td>" + n++ + "</td>";
            tabla += "<td class='text-md-center'><button class='btn btn-warning btn-sm' data-toggle='modal'\n\
                     data-target='#modalTableMovimientos' onclick='mostrarMovimientos(" + i + ")'> <i class='fas fa-table'></i></button></td>";
            tabla += "<td class='text-md-center'>" + data[i].nombre + " " + data[i].apellidoPaterno + " " + data[i].apellidoMaterno + "</td>";
            tabla += "<td>" + data[i].numeroSerie + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].placas + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].modelo + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].tenencia + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].tarjetaCirculacion + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].verificacion + "</td>";
            tabla += "<td class='text-md-center'> " + data[i].fechaVerificacion + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].refrendo + "</td>";
            tabla += "<td>" + data[i].descripcion + "</td>";
            tabla += "<td class='text-md-center'>" + es + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].descripcionEstatus + "</td>";
//            tabla += "<td class='text-md-center'><button class='btn btn-warning btn-sm' data-toggle='modal'\n\
//                     data-target='#modalFormularioModificar'  onclick='cargarModificarVehiculo(" + i + ")'> <i class='fas fa-edit'></i></button></td>";
//            
            tabla += "<td class='text-md-center'><a class='btn btn-warning btn-sm' data-toggle='modal'\n\
                     data-target='#modalFormularioModificar'  onclick='cargarModificarVehiculo(" + i + ")'> <i class='fas fa-edit'></i></a></td>";

            tabla += "</tr>";

        }

        $('#lstVehiculo').html(tabla);
    });
}

function cargarModificarVehiculo(posicion) {

    var vehiculoActual = vehiculo[posicion];


    $.ajax(
            {
                type: "GET",
                async: true,
                url: "tablaVehiculos.html"
            }
    ).done(function (data) {

        tn = "";
        var tc = "";
        var veri = "";
        var r = "";
        var s = "";

        $("#idVehiculo").val(vehiculoActual.idVehiculo);
        $("#txtNombreM").val(vehiculoActual.nombre);
        $("#txtApellidoPaM").val(vehiculoActual.apellidoPaterno);
        $("#txtApellidoMaM").val(vehiculoActual.apellidoMaterno);
        $("#txtNumSerieM").val(vehiculoActual.numeroSerie);
        $("#txtPlacasM").val(vehiculoActual.placas);
        $("#txtModeloM").val(vehiculoActual.modelo);
        $("#txtFechaVM").val(vehiculoActual.fechaVerificacion);
        $("#txtDescripcionM").val(vehiculoActual.descripcion);
        $('#txtDescripcionEsM').val(vehiculoActual.descripcionEstatus);

        if (vehiculoActual.tenencia === 'SI') {
            tn += '<option value="' + vehiculoActual.tenencia + '"selected>' + "SI" + '</option>';
            tn += '<option value="NO" >' + "NO" + '</option>';
        } else if (vehiculoActual.tenencia === 'NO') {
            tn = '<option value="' + vehiculoActual.tenencia + '"selected>' + "NO" + '</option>';
            tn += '<option value="SI" >' + "SI" + '</option>';
        }
        $("#optionTeM").html(tn);

        /**************************************************/
        if (vehiculoActual.tarjetaCirculacion === 'SI') {
            tc += '<option value="' + vehiculoActual.tarjetaCirculacion + '"selected>' + "SI" + '</option>';
            tc += '<option value="NO" >' + "NO" + '</option>';
        } else if (vehiculoActual.tarjetaCirculacion === 'NO') {
            tc = '<option value="' + vehiculoActual.tarjetaCirculacion + '"selected>' + "NO" + '</option>';
            tc += '<option value="SI" >' + "SI" + '</option>';
        }
        $("#optionTCM").html(tc);

        /**************************************************/
        if (vehiculoActual.verificacion === 'SI') {
            veri += '<option value="' + vehiculoActual.verificacion + '"selected>' + "SI" + '</option>';
            veri += '<option value="NO" >' + "NO" + '</option>';
        } else if (vehiculoActual.verificacion === 'NO') {
            veri = '<option value="' + vehiculoActual.verificacion + '"selected>' + "NO" + '</option>';
            veri += '<option value="SI" >' + "SI" + '</option>';
        }
        $("#optionVM").html(tn);

        /**************************************************/
        alert(vehiculoActual.refrendo)
        if (vehiculoActual.refrendo === 'SI') {
            r += '<option value="' + vehiculoActual.refrendo + '"selected>' + "SI" + '</option>';
            r += '<option value="NO" >' + "NO" + '</option>';
        } else if (vehiculoActual.refrendo === 'NO') {
            r = '<option value="' + vehiculoActual.refrendo + '"selected>' + "NO" + '</option>';
            r += '<option value="SI" >' + "SI" + '</option>';
        }
        $("#optionRM").html(r);
        /**************************************************/
        if (vehiculoActual.estatus === 1) {
            s += '<option value="' + vehiculoActual.estatus + '"selected>' + "CIRCULANDO" + '</option>';
            s += '<option value="0" >' + "NO CIRCULANDO" + '</option>';
        } else if (vehiculoActual.estatus === 0) {
            s = '<option value="' + vehiculoActual.estatus + '"selected>' + "NO CIRCULANDO" + '</option>';
            s += '<option value="1" >' + "CIRCULANDO" + '</option>';
        }
        $("#optionEstatusM").html(s);

    });
}

function cargarHistorial() {

    $.ajax(
            {
                type: "GET",
                async: true,
                url: "tablaMovimientos.html"
            }
    ).done(function (data) {

        $("#secundario").html(data);

        $("#secundario").html(data);
        $("#secundario").removeClass("col-sm-0");
        $("#secundario").addClass("col-sm-6");
        $("#secundario").show();
        $("#primario").removeClass("col-sm-12");
        $("#primario").addClass("col-sm-6");

    });
}

function guardarVehiculo() {

    var nombre = $('#txtNombre').val();
    var apellidoPaterno = $('#txtApellidoPa').val();
    var apellidoMaterno = $('#txtApellidoMa').val();
    var numeroSerie = $('#txtNumSerie').val();
    var placas = $('#txtPlacas').val();
    var descripcion = $('#txtDescripcion').val();
    var modelo = $('#txtModelo').val();

    var tenencia = $('#optionTe').val();
    var tarjetaCirculacion = $('#optionTC').val();
    var verificacion = $('#optionV').val();
    var refrendo = $('#optionR').val();
    var fechaVerificacion = $('#txtFechaV').val();
    var estatus = $('#optionEstatus').val();
    var descripcionEstatus = $('#txtDescripcionEs').val();

    var vehiculo = {
        nombre: nombre,
        apellidoPaterno: apellidoPaterno,
        apellidoMaterno: apellidoMaterno,
        numeroSerie: numeroSerie,
        placas: placas,
        descripcion: descripcion,
        modelo: modelo,
        tenencia: tenencia,
        tarjetaCirculacion: tarjetaCirculacion,
        verificacion: verificacion,
        fechaVerificacion: fechaVerificacion,
        refrendo: refrendo,
        estatus: estatus,
        descripcionEstatus: descripcionEstatus
    };

    var json = {json: JSON.stringify(vehiculo)};
    console.log(json)

    $.ajax({
        type: "POST",
        async: true,
        url: "api/controlVehicular/insertVehiculo",
        data: json
    }
    ).done(function (data) {

        if (data.result !== null) {

            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Se ha guardado correctamente',
                showConfirmButton: false,
                timer: 1500
            });
            $('#modalFormulario').modal('hide');
            //$("#idVehiculo").val("");
            $("#txtNombre").val("");
            $("#txtApellidoPa").val("");
            $("#txtApellidoMa").val("");
            $("#txtNumSerie").val("");
            $("#txtPlacas").val("");
            $("#txtModelo").val("");
            $("#txtFechaV").val("");
            $("#txtDescripcion").val("");
            $('#txtDescripcionEs').val("");
            detalleVehiculo();

        } else {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'Ha ocurrido un error',
                showConfirmButton: false,
                timer: 1500
            });
        }
    })
            .fail(function (data) {
                if (data.responseCode) {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'error',
                        title: 'Ha ocurrido un error: ' + data.responseCode,
                        showConfirmButton: false,
                        timer: 1500
                    });
                }

            });
}

function modificarVehiculo() {

    var idVehiculo = $('#idVehiculo').val();
    var nombre = $('#txtNombreM').val();
    var apellidoPaterno = $('#txtApellidoPaM').val();
    var apellidoMaterno = $('#txtApellidoMaM').val();
    var numeroSerie = $('#txtNumSerieM').val();
    var placas = $('#txtPlacasM').val();
    var descripcion = $('#txtDescripcionM').val();
    var modelo = $('#txtModeloM').val();
    var tenencia = $('#optionTeM').val();
    var tarjetaCirculacion = $('#optionTCM').val();
    var verificacion = $('#optionVM').val();
    var fechaVerificacion = $('#txtFechaVM').val();
    var refrendo = $('#optionRM').val();
    var estatus = $('#optionEstatusM').val();
    var descripcionEstatus = $('#txtDescripcionEsM').val();

    console.log(estatus)

    var vehiculo = {
        idVehiculo: idVehiculo,
        nombre: nombre,
        apellidoPaterno: apellidoPaterno,
        apellidoMaterno: apellidoMaterno,
        numeroSerie: numeroSerie,
        placas: placas,
        descripcion: descripcion,
        modelo: modelo,
        tenencia: tenencia,
        tarjetaCirculacion: tarjetaCirculacion,
        verificacion: verificacion,
        fechaVerificacion: fechaVerificacion,
        refrendo: refrendo,
        estatus: estatus,
        descripcionEstatus: descripcionEstatus
    };

    var json = {json: JSON.stringify(vehiculo)};


    console.log(json)
    $.ajax({
        type: "POST",
        async: true,
        url: "api/controlVehicular/updateVehiculo",
        data: json
    }
    ).done(function (data) {

        if (data.result !== null) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Se ha guardado correctamente',
                showConfirmButton: false,
                timer: 1500
            });

            $('#modalFormulario').modal('hide');
            $("#idVehiculoM").val("");
            $("#txtNombreM").val("");
            $("#txtApellidoPaM").val("");
            $("#txtApellidoMaM").val("");
            $("#txtNumSerieM").val("");
            $("#txtPlacasM").val("");
            $("#txtModeloM").val("");
            $("#txtFechaVM").val("");
            $("#txtDescripcionM").val("");
            $('#txtDescripcionEsM').val("");
            detalleVehiculo();
        } else {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'Ha ocurrido un error',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });

}

/****************************************************************************/
/****************************************************************************/


function cerrarHistorial() {
    $("#secundario").removeClass("col-sm-6");
    $("#secundario").addClass("col-sm-0");
    $("#secundario").hide();

    $("#primario").removeClass("col-sm-6");
    $("#primario").addClass("col-12");

}

function mostrarMovimientos(i) {
    cargarHistorial();

    var vAct = vehiculo[i];

    var idVeh = vAct.idVehiculo;


    var data = {
        idVehiculo: idVeh
    };


    $.ajax(
            {
                type: "GET",
                async: true,
                url: "api/controlVehicular/getHistory",
                data: data
            }
    ).done(function (data) {

        $("#idMovimiento").val(vAct.idVehiculo);
        movimiento = data;
        var tabla = "";
        var t = 0;
        var n = 1;
        for (var i = 0; i < data.length; i++) {

            tabla += "<tr>";
            tabla += "<td style='display:none' id='idVehiculo'>" + data[i].idMovimiento + "</td>";
            tabla += "<td> " + n++ + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].fechaCompra + "</td>";
            tabla += "<td class='text-md-center'>" + data[i].descripcionMovimiento + "</td>";
            tabla += "<td class='text-md-center'>" + "$ " + data[i].costoUnitario + "</td>";
            tabla += "<td style='display:none'>" + data[i].dv.idVehiculo + "</td>";
            tabla += "<td class='text-md-center'><button class='btn btn-warning btn-sm' data-toggle='modal'\n\
                     data-target='#modalFormularioMovModificar' onclick='cargarModificarMov(" + i + ")'> <i class='fas fa-edit align-content'></i></button></td>";

            tabla += "</tr>";

            t += data[i].costoUnitario;
        }

        $('#lstMovimientos').html(tabla);
        $('#total').html("$ " + t);


    });
}

function cargarModificarMov(i) {

    var movimientoActual = movimiento[i];


    $.ajax(
            {
                type: "GET",
                async: true,
                url: "tablaMovimientos.html"
            }
    ).done(function (data) {

        tn = "";

        $("#idMovimientoM").val(movimientoActual.idMovimiento);
        $("#txtFechaCompraM").val(movimientoActual.fechaCompra);
        $("#txtDescripcionMM").val(movimientoActual.descripcionMovimiento);
        $("#txtCostoUniM").val(movimientoActual.costoUnitario);



    });
}

function guardarMovimiento() {

    var fechaCompra = $('#txtFechaCompra').val();
    var descripcionMovimiento = $('#txtDescripcionMo').val();
    var costoUnitario = $('#txtCostoUni').val();
    var idVehiculo = $('#idMovimiento').val();

    var movimiento = {
        fechaCompra: fechaCompra,
        descripcionMovimiento: descripcionMovimiento,
        costoUnitario: costoUnitario,
        dv: {
            idVehiculo: idVehiculo
        }
    };

    var json = {json: JSON.stringify(movimiento)};

    console.log(json)

    $.ajax({
        type: 'POST',
        async: false,
        url: "api/controlVehicular/insertHistory",
        data: json
    }).done(function (data) {
        if (data.result !== null) {

            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Se ha guardado correctamente',
                showConfirmButton: false,
                timer: 1500
            });

            $('#modalFormularioMov').modal('hide');
            cerrarHistorial();
        } else {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'Ha ocurrido un error',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function modificarMovimiento() {

    var fechaCompra = $('#txtFechaCompraM').val();
    var descripcionMovimiento = $('#txtDescripcionMM').val();
    var costoUnitario = $('#txtCostoUniM').val();
    var idMovimiento = $('#idMovimientoM').val();

    var movimiento = {
        fechaCompra: fechaCompra,
        descripcionMovimiento: descripcionMovimiento,
        costoUnitario: costoUnitario,
        idMovimiento: idMovimiento
        
    };

    var json = {json: JSON.stringify(movimiento)};

    $.ajax({
        type: 'POST',
        async: false,
        url: "api/controlVehicular/updateHistory",
        data: json
    }).done(function (data) {
        if (data.result !== null) {

            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Se ha guardado correctamente',
                showConfirmButton: false,
                timer: 1500
            });
            $('#modalFormularioMov').modal('hide');
            cerrarHistorial();
            detalleVehiculo();
            
        } else {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'Ha ocurrido un error',
                showConfirmButton: false,
                timer: 1500
            });
        }
    }).fail(function (data) {
                if (data.responseCode) {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'error',
                        title: 'Ha ocurrido un error: ' + data.responseCode,
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
                 $('#modalFormularioMov').modal('hide');
            cerrarHistorial();
            detalleVehiculo();

            });;
}


