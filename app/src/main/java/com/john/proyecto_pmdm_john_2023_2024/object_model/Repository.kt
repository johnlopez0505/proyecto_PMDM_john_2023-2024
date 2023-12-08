package com.john.recicleview.object_model

import com.john.proyecto_pmdm_john_2023_2024.models.Restaurant

object Repository {
    val listRestauran : List<Restaurant> = listOf(
        Restaurant(
            "Casa marcial" ,
            "Arriondas",
            "Asturias" ,
            "985 84 09 91",
            "https://media-cdn.tripadvisor.com/media/photo-s/19/37/a9/8d/" +
                    "entrada-casa-marcial.jpg"
        ),
        Restaurant(
            "El cenador de Amós" ,
            "Villa verde de Pontones",
            "Cantabria" ,
            "942 50 82 43",
            "https://www.turicantabria.com/wp-content/uploads/2015/07/Fachada-.jpg"
        ),
        Restaurant(
            " El Coto de Quevedo" ,
            "La Torre de Juan Abad",
            "Ciudad Real" ,
            " 649 84 29 01",
            "https://x.cdrst.com/foto/hotel-sf/43624/granderesp/" +
                    "hotel-rural-coto-de-quevedo-restauracion-bb7d96d.jpg"
        ),
        Restaurant(
            "Refectorio" ,
            "Abadía Retuerta LeDomaine",
            "Valladolid" ,
            "983 68 76 00",
            "https://d2cee8n5lrzoq8.cloudfront.net/wp-content/uploads/2021/10/14201022/" +
                    "Refectorio.-381x381-Desktop.jpg"
        ),
        Restaurant(
            "Magoga" ,
            "Cartagena",
            "Región de Murcia" ,
            "629 98 02 57",
            "https://lasmariacocinillas.com/wp-content/uploads/2014/09/magoga0.jpg"
        ),
        Restaurant(
            " Casa El Loco" ,
            "jaen",
            "jaen" ,
            "953 23 42 07",
            "https://lh5.googleusercontent.com/p/AF1QipO7SAkpM_oEUOzDS4JyVE7yiiIqCGrCXuesyBj6"
        )
    )
}