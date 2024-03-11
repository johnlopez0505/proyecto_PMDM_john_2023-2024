package com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant

class Restaurant(
    var nombre: String,
    var ciudad: String,
    var provincia: String,
    var telefono: String,
    var imagen: String?,
    var id: String,
    var id_usuario: String?,
    var insert_id:String,
    var result: String,
    var file_img: String
)
{
    operator fun component1(): String = nombre
    operator fun component2(): String = ciudad
    operator fun component3(): String = provincia
    operator fun component4(): String = telefono
    operator fun component5(): String = imagen!!

    constructor(
        name: String,
        city: String,
        province: String,
        phone: String,
        image: String?,
        id_usuario: String?,
        id : String) :
            this( name, city, province,phone,image, id_usuario!!,id,"","","")

    constructor(name: String, city: String, province: String, phone: String, image: String?, id_usuario: String?):
            this( name, city, province,phone,image, "",id_usuario,"","","")
    constructor(result: String, insert_id: String,file_img : String):
            this("", "","","" ,"" , "", "",result,insert_id,file_img)

    constructor(name: String, city: String,province: String,phone:String,image:String ):
            this( name, city, province , phone, image,"", "","","","")


    constructor(result: String):
            this("","","","","","","","",result,"")

    constructor(result: String,file_img: String):
            this("","","","","","","","",result,file_img)

    constructor(id: Int):
            this("","","","","","","","","","")

    constructor(
        nombre: String,
        ciudad: String,
        provincia: String,
        telefono: String,
        imagen: String,
        idUsuario: String,
        id: Int
    ) :
            this("","","","","","","","","","")

    override fun toString(): String {
        return "Restaurant(name='$nombre', city='$ciudad', province='$provincia'," +
                "phone='$telefono', image='$imagen', id='$id')"
    }
}

