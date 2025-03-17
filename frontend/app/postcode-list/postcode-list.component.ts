import { Component, OnInit } from '@angular/core';
import { PostcodeService, Postcode } from '../services/postcode.service';

@Component({
  selector: 'app-postcode-list',
  templateUrl: './postcode-list.component.html',
  styleUrls: ['./postcode-list.component.css']
})
export class PostcodeListComponent implements OnInit {
  postcodes: Postcode[] = [];
  totalCount: number = 0;
  currentPage: number = 1;
  pageSize: number = 50;
  totalPages: number = 0;
  loading: boolean = false;
  error: string | null = null;
  Math = Math; // Add Math object for template

  constructor(private postcodeService: PostcodeService) { }

  ngOnInit(): void {
    this.loadPostcodes();
  }

  loadPostcodes(): void {
    this.loading = true;
    this.error = null;
    
    this.postcodeService.getAllPostcodes(this.currentPage - 1, this.pageSize)
      .subscribe({
        next: (response) => {
          this.postcodes = response.postcodes;
          this.totalCount = response.totalCount;
          this.totalPages = Math.ceil(this.totalCount / this.pageSize);
          this.loading = false;
        },
        error: (err) => {
          console.error('Error loading postcodes', err);
          this.error = 'Failed to load postcodes. Please try again later.';
          this.loading = false;
        }
      });
  }

  goToPage(page: number): void {
    if (page < 1 || page > this.totalPages || page === this.currentPage) {
      return;
    }
    
    this.currentPage = page;
    this.loadPostcodes();
  }

  getPaginationArray(): number[] {
    const pages: number[] = [];
    
    // Always show first page
    if (this.totalPages > 0) {
      pages.push(1);
    }
    
    // Calculate range around current page
    let startPage = Math.max(2, this.currentPage - 1);
    let endPage = Math.min(this.totalPages - 1, this.currentPage + 1);
    
    // Add ellipsis after first page if needed
    if (startPage > 2) {
      pages.push(-1); // -1 represents ellipsis
    }
    
    // Add pages around current page
    for (let i = startPage; i <= endPage; i++) {
      pages.push(i);
    }
    
    // Add ellipsis before last page if needed
    if (endPage < this.totalPages - 1) {
      pages.push(-1); // -1 represents ellipsis
    }
    
    // Always show last page if it exists
    if (this.totalPages > 1) {
      pages.push(this.totalPages);
    }
    
    return pages;
  }
} 