val Evento.durationOfEvent: String
    get() = if (this.duracaoMinutos < 60) {
        "Mais rápido"
    } else {
        "Mais demorado"
    }
