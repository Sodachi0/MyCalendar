import {Availability} from "./availability";
import {Reservation} from "./reservation";

export interface Calendar {
  availabilities: Availability[];
  reservations: Reservation[];
}
