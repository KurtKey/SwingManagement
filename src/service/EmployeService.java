package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Employe;
import beans.Machine;
import connexion.Connexion;
import dao.IDao;

public class EmployeService implements IDao<Employe> {
	@Override
	public boolean create(Employe o) {
		String sql = "insert into Employe values (null, '" + o.getNom() + "','" + o.getPrenom() + "' ," + o.getSalaire()
				+ " )";
		try {
			Statement statement = Connexion.oneConnection().getConnection().createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Employe o) {
		PreparedStatement ps;
		String str = "delete from employe where id=?";
		try {
			ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ps.setInt(1, o.getId());
			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Employe o) {
		try {
			String str = "update employe set nom = ?,prenom = ? ,salaire = ? where id= ? ";
			PreparedStatement pr = Connexion.oneConnection().getConnection().prepareStatement(str);
			pr.setString(1, o.getNom());
			pr.setString(2, o.getPrenom());
			pr.setDouble(3, o.getSalaire());
			pr.setInt(4, o.getId());
			if (pr.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Employe> findAll() {
		List<Employe> employes = new ArrayList<Employe>();
		String sql = "select * from employe";
		try {
			Statement statement = Connexion.oneConnection().getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				employes.add(new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getDouble("salaire")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employes;
	}

	@Override
	public Employe findById(int id) {
		String str = "select * from employe where id = ?";
		PreparedStatement ps;
		ResultSet rs;
		Employe emp = null;
		try {
			ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				emp = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getDouble("salaire"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return emp;
	}

	@Override
	public List<Employe> findElementsByKeyWord(String colomnName, String kw) {
		List<Employe> emps = new ArrayList<>();
		PreparedStatement ps;
		String str;
		if (colomnName.equals("nom"))
			str = "select * from employe where nom like ?";
		else
			str = "select * from employe where prenom like ?";
		try {
			ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ps.setString(1, "%" + kw + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employe e = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getDouble("salaire"));
				emps.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emps;
	}

}
