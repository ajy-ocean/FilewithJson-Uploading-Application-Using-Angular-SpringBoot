import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(
    private httpClient : HttpClient
  ) { }

  postUserData(user:User, file:Blob){
    let formData = new FormData();
    formData.append("file",file);
    formData.append("userData",JSON.stringify(user));
    return this.httpClient.post(`http://localhost:8080/users`,formData)
  }
}
