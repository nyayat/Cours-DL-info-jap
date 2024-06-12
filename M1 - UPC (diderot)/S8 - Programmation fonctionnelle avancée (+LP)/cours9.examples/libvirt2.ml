open Connection
let conn_ro = connect_readonly ()
let _ = status conn_ro
let _ = destroy conn_ro (* error *)

let conn_rw = connect ()
let _ = status conn_rw
let _ = destroy conn_rw
