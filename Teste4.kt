val shortEvents = eventos.filter { it.duracaoMinutos < 60 }
println("Você tem ${shortEvents.size} compromissos rápidos.")
