import {Injectable} from '@angular/core';
import {ApiService} from './api-service.service';
import {ConfigServiceService} from './Services/config-service.service';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  currentUser :any;

  constructor(
    private apiService: ApiService,
    private config: ConfigServiceService
  ) {
  }


}
