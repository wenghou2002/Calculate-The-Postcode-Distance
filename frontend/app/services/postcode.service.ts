import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DistanceResult } from '../distance-calculator/distance-calculator.component';
import { PostcodeUpdateResult } from '../postcode-updater/postcode-updater.component';

export interface Postcode {
  code: string;
  latitude: number;
  longitude: number;
}

export interface PostcodeListResponse {
  postcodes: Postcode[];
  totalCount: number;
}

@Injectable({
  providedIn: 'root'
})
export class PostcodeService {
  private apiUrl = 'http://localhost:8080/api/postcodes';
  private username = 'user';
  private password = 'password';

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const authString = `${this.username}:${this.password}`;
    const base64Auth = btoa(authString);
    return new HttpHeaders({
      'Authorization': `Basic ${base64Auth}`
    });
  }

  getDistance(postcode1: string, postcode2: string): Observable<DistanceResult> {
    return this.http.get<DistanceResult>(`${this.apiUrl}/distance`, {
      params: { postcode1, postcode2 },
      headers: this.getAuthHeaders()
    });
  }

  updatePostcode(code: string, latitude: number | null, longitude: number | null): Observable<PostcodeUpdateResult> {
    const params: Record<string, number> = {};
    
    if (latitude !== null) {
      params['latitude'] = latitude;
    }
    
    if (longitude !== null) {
      params['longitude'] = longitude;
    }
    
    return this.http.put<PostcodeUpdateResult>(`${this.apiUrl}/${code}`, null, { 
      params,
      headers: this.getAuthHeaders()
    });
  }
  
  getAllPostcodes(page: number, pageSize: number): Observable<PostcodeListResponse> {
    return this.http.get<PostcodeListResponse>(`${this.apiUrl}/list`, {
      params: { page: page.toString(), pageSize: pageSize.toString() },
      headers: this.getAuthHeaders()
    });
  }
} 