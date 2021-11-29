/**
 *
 * @author Soto Cervantes César Andres
 * @Email soto.cervantes.cesar@gmail.com
 */
var materiaAct = null;
var materiaInac = null;
var materia = null;
var resp = "ALV";

function cargarMateriaAdministrador() {
    $.ajax(
            {
                type: "GET",
                url: "AdministradorMateria/materia.html",
                async: true
            }
    ).done(
            function (data)
            {
                mostrarMateriaActiva();
                $('#divModulos').html(data);
                cargarProveedor();
            }
    );
}



function guardarMateria() {
    var nombre = $('#txtNombre').val();
    //alert(nombre);
    var precio = $('#txtPrecio').val();
    //alert(precio);
    var unidad = $('#unidadMedida').val();
    //alert(existencia);
    var cantidad = $('#txtCantidad').val();
    //alert(foto);
    var proveedor = $('#lsProveedores').val();
    //alert(anunciante);
    var datos = {
        nombre: nombre,
        precio: precio,
        unidad: unidad,
        cantidad: cantidad,
        proveedor: proveedor
    };
    //alert(JSON.stringify(datos));
    $.ajax({
        type: "POST",
        async: true,
        url: "api/materia/insert",
        data: datos
    }
    ).done(function (data) {
        if (data.result != null) {
           swal.fire(
                            'Movimiento realizado con exito',
                            'Producto agregado',
                            'success'
                            );
        } else {
            alert("No se pudo agregar el Producto");
        }
        mostrarMateriaActiva();
    }
    );
    limpiarCamposMateria();
}

function calcularUnidad() {
    var cantidad = $('#txtCantidad5').val();
    var unidad1 = $('#unidadMedida4').val();
    var kilosCostal = $('#txtKilosCostal').val();
    var litrosGalon = $('#txtLitrosGalon').val();

    if (unidad1 == "1") {
        respuesta=cantidad/1000;
        document.getElementById("txtResultado").innerHTML = respuesta + " Kilogramos";
    }else{
        if(unidad1 == "2"){
            respuesta=cantidad*kilosCostal;
            document.getElementById("txtResultado").innerHTML = respuesta + " Kilogramos";
        }else{
            if(unidad1 == "3"){
                respuesta=cantidad/1000;
                document.getElementById("txtResultado").innerHTML = respuesta + " Litros";
            }else{
                if(unidad1 == "4"){
                respuesta=cantidad*litrosGalon;
                document.getElementById("txtResultado").innerHTML = respuesta + " Litros";
            }
            }
        }
    }

}

function limpiarCamposUnidad(){
    $('#unidadMedida4').val("");
    $('#unidadMedida5').val("");
    $('#txtCantidad5').val("");
    $('#txtKilosCostal').val("");
    $('#txtLitrosGalon').val("");
    $('#txtResultado').val("");
}

function mostrarMateriaActiva() {
    $.ajax(
            {
                type: "POST",
                url: "api/materia/getMateriaActiva",
                async: true
            }).done(
            function (data) {
                //alert(JSON.stringify(data));
                materiaAct = data;
                var tabla = "<table class='table table-sm table-hover table-dark' id=tablaMateriasAct> \n\ ";
                for (var i = 0; i < data.length; i++) {
                    tabla += "<tr scope=row>";
                    tabla += "<td>" + data[i].nombreMateria + "</td>";
                    tabla += "<td>" + "$" + data[i].precio + "</td>";
                    tabla += "<td id='campoCantidad'>" + data[i].cantidad + "</td>";
                    tabla += "<td>" + data[i].unidad + "</td>";
                    tabla += "<td>" + data[i].proveedor.persona.nombre + "</td>";
                    tabla += "<td>" + data[i].proveedor.empresa + "</td>";
                    var cant = data[i].cantidad;
                    if(cant > 10){
                        //resp = "Fresco el Pana";
                        tabla += "<td><img src='imagenes/Verde.png';width='42' height='42'/></td>";
                    }else{
                        if(cant < 10 && cant > 5){
                            //resp = "Ponte Verga";
                            tabla += "<td><img src='imagenes/naranja.jpg';width='42' height='42'/></td>";
                        }else{
                            if(cant < 5 && cant > 0){
                                //resp = "Ya valió madres el producto";
                                tabla += "<td><img src='imagenes/rojo.png';width='42' height='42'/></td>";
                            }
                        }
                    }
                    tabla += "<td> <a class='waves-effect waves-light btn modal-trigger btn' style='background-color: #5E3023' href='#modal_boxCantidad' onclick='cargarModificarMateria(" + i + ");' >Agregar más Cantidad</a></td>";
                    tabla += "<td> <a class='waves-effect waves-light btn modal-trigger btn' style='background-color: #5E3023' href='#modal_box_ModificarMateria' onclick='cargarModificarMateria(" + i + ");' >Acciones</a></td>";
                    tabla += "</tr>";
                }
                tabla += "</table>";
                $('#tablaMateriaActi').html(tabla);
            });
}

function cargarModificarMateria(posicion) {
    materia = materiaAct[posicion];

    $('#txtIdMateria').val(materia.idMateria);

    $('#txtNombre2').val(materia.nombreMateria);
    $('#txtPrecio2').val(materia.precio);
    $('#txtCantidad2').val(materia.cantidad);
    if (materia.unidad == "Kilogramos") {
        document.getElementById("unidadMedida2").selectedIndex = "0";
    } else {
        if (materia.unidad == "Gramos") {
            document.getElementById("unidadMedida2").selectedIndex = "1";
        } else {
            if (materia.unidad == "Litros") {
                document.getElementById("unidadMedida2").selectedIndex = "2";
            } else {
                if (materia.unidad == "Mililitros") {
                    document.getElementById("unidadMedida2").selectedIndex = "3";
                } else {
                    if (materia.unidad == "Costales") {
                        document.getElementById("unidadMedida2").selectedIndex = "4";
                    } else {
                        document.getElementById("unidadMedida2").selectedIndex = "5";
                    }
                }
            }
        }
    }
    var proveedorSelect = '<option value="' +materia.proveedor.idProveedor+'" selected>' + materia.proveedor.persona.nombre+'</option>';
                var opciones = $('#lsProveedores2').html()+proveedorSelect;
                 $('#lsProveedores2').html(opciones);
                 cargarProveedor2();
}

function guardarCantidad (){
    var idMateria = $('#txtIdMateria').val();
    //alert(idMateria);
    var cantidad = $('#txtCantidad6').val();
    //alert(cantidad);
    var info = {
        idMateria: idMateria,
        cantidad: cantidad
    };
    $.ajax({
        type: "POST",
        async: true,
        url: "api/materia/updateCantidad",
        data: info
    }
    ).done(function (data) {
        if (data.result != null){
           
            swal.fire(
                            'Movimiento realizado con exito',
                            'Cantidad modificada',
                            'success'
                            );
        }else{
            alert("¡No se puedo actualizar la cantidad!");}
        mostrarMateriaActiva();
    }
    );
    limpiarCamposCantidad();
    
}

function limpiarCamposCantidad (){
    $('#txtCantidad6').val("");
}
function guardarModificarMateria() {  
   var idMateria = $('#txtIdMateria').val();
    //alert(idMateria);
    var nombre = $('#txtNombre2').val();
    //alert(nombre);
    var precio = $('#txtPrecio2').val();
    //alert(precio);
    var unidad = $('#unidadMedida2').val();
    //alert(unidad);
    var cantidad = $('#txtCantidad2').val();
    //alert(cantidad);
    var proveedor = $('#lsProveedores2').val();
    //alert(proveedor);
    var info = {
        idMateria: idMateria,
        nombre: nombre,
        precio: precio,
        unidad: unidad,
        cantidad: cantidad,
        proveedor: proveedor
    };
    $.ajax({
        type: "POST",
        async: true,
        url: "api/materia/update",
        data: info
    }
    ).done(function (data) {
        if (data.result != null){
           
            swal.fire(
                            'Movimiento realizado con exito',
                            'Producto modificado',
                            'success'
                            );
        }else{
            alert("¡No se puedo actualizar el Producto!");}
        mostrarMateriaActiva();
    }
    );
    limpiarCamposMateria();
}

function cargarProveedor() {
    $.ajax(
            {
                type: "POST",
                url: "api/proveedor/getProvedorActivo",
                async: false
            }).done(
            function (data) {
                var proveedor = "";
                proveedor = "<option value=''>Seleccione el Proveedor </option>";
                for (var i = 0; i < data.length; i++) {
                    proveedor += "<option value='" + data[i].idProveedor + "'>";
                    proveedor += "<td>" + data[i].persona.nombre + "</td>";
                    proveedor += "</option>";
                }
                $('#lsProveedores').html(proveedor);
            }
    );
}

function cargarProveedor2() {
    $.ajax(
            {
                type: "POST",
                url: "api/proveedor/getProvedorActivo",
                async: false
            }).done(
            function (data) {
                var proveedor = "";
                proveedor = "<option value=''>Seleccione el Proveedor </option>";
                for (var i = 0; i < data.length; i++) {
                    proveedor += "<option value='" + data[i].idProveedor + "'>";
                    proveedor += "<td>" + data[i].persona.nombre + "</td>";
                    proveedor += "</option>";
                }
                $('#lsProveedores2').html(proveedor);
            }
    );
}

function limpiarCamposMateria(){
    $('#txtNombre').val("");
    $('#txtPrecio').val("");
    $('#unidadMedida').val("");
    $('#txtCantidad').val("");
    $('#lsProveedores').val("");
}

function validarCamposMateria(){
    var nombre = $('#txtNombre')[0].value;
    var precio = $('#txtPrecio')[0].value;
    var unidad = $('#unidadMedida')[0].value;
    var cantidad = $('#txtCantidad')[0].value;
    var proveedor = $('#lsProveedores')[0].value;

    if((nombre == "") || (precio == "") || (unidad == "") || (cantidad == "") || (proveedor == "")){
        Swal.fire({
                icon: 'error',
                title: '¡Lo sentimos!',
                text: '¡Llene todos los campos!'
            });               
        return false;
    }else{
        guardarMateria();
    }
}