module ValentinesDay

type Approval = Yes | No | Maybe
type Cuisine = Korean | Turkish
type Genre = Crime | Horror | Romance | Thriller
type Activity = BoardGame | Chill | Movie of Genre | Restaurant of Cuisine | Walk of int
let rateActivity (activity: Activity): Approval =
    match activity with
    | Movie Romance -> Approval.Yes
    | Restaurant Korean -> Approval.Yes
    | Restaurant Turkish -> Approval.Maybe
    | Walk km when km < 3 -> Approval.Yes
    | Walk km when km < 5 -> Approval.Maybe
    | _ -> Approval.No
