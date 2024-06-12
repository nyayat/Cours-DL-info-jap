module type LENGTH = sig
    type 'a t
    val meters : float -> [`Meters] t
    val feet : float -> [`Feet] t
    val (+.) : 'a t -> 'a t -> 'a t
    val to_float : 'a t -> float
  end

module Length:LENGTH = struct
  type 'a t = float
  let meters f = f
  let feet f = f
  let (+.) = Stdlib.(+.)
  let to_float f = f
end
