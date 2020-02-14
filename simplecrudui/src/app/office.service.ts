import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfficeService {

  private baseUrl = 'http://localhost:8080/offices';

  constructor(private http: HttpClient) { }

  getOffice(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

// for implementation
  // createOffice(office: Object): Observable<Object> {
  //   return this.http.post(`${this.baseUrl}`, office);
  // }

  // updateOffices(id: number, value: any): Observable<Object> {
  //   return this.http.put(`${this.baseUrl}/${id}`, value);
  // }

  // deleteOffice(id: number): Observable<any> {
  //   return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  // }

  getOfficeList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
