package com.calculator.model;

public class Gcd {

		private Long id;
		private Integer gcd;
		public Gcd() {
		}

		public Gcd(Long id, Integer gcd) {
			this.id = id;
			this.gcd = gcd;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getGcd() {
			return gcd;
		}

		public void setGcd(Integer gcd) {
			this.gcd = gcd;
		}

		@Override
		public String toString() {
			return "Gcd{" + "id=" + id + ", gcd=" + gcd + 
					+ '\'' + '}';
		}
}
