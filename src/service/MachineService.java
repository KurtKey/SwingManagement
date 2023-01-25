package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import beans.Employe;
import beans.Machine;
import connexion.Connexion;
import dao.IDao;

public class MachineService implements IDao<Machine> {

	EmployeService es = new EmployeService();

	@Override
	public boolean create(Machine o) {
		try {
			String req = "insert into machine values(null, ?, ?, ?, ?, ?)";
			PreparedStatement pr = Connexion.oneConnection().getConnection().prepareStatement(req);
			pr.setString(1, o.getReference());
			pr.setString(2, o.getMarque());
			pr.setDate(3, new Date(o.getDateAchat().getTime()));
			pr.setDouble(4, o.getPrix());
			pr.setInt(5, o.getEmploye().getId());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Machine o) {
		PreparedStatement ps;
		String str = "delete from machine where id=?";
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
	public boolean update(Machine o) {
		try {
			String str = "update machine set reference = ?,marque = ? ,dateAchat = ?, prix= ? ,employe = ? where id= ? ";
			PreparedStatement pr = Connexion.oneConnection().getConnection().prepareStatement(str);
			pr.setString(1, o.getReference());
			pr.setString(2, o.getMarque());
			pr.setDate(3, new Date(o.getDateAchat().getTime()));
			pr.setDouble(4, o.getPrix());
			pr.setInt(5, o.getEmploye().getId());
			pr.setInt(6, o.getId());
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
	public List<Machine> findAll() {
		List<Machine> l = new ArrayList<>();
		String str = "select * from machine";
		try {
			PreparedStatement ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ResultSet rs = ps.executeQuery(str);
			while (rs.next()) {
				l.add(new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"),
						rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return l;
	}

	@Override
	public Machine findById(int id) {
		String str = "select * from machine where id = ?";
		PreparedStatement ps;
		ResultSet rs;
		Machine mach = null;
		try {
			ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				mach = new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"),
						rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mach;
	}

	public List<Machine> getMachinesByEmploye(Employe emp) {
		List<Machine> machs = new ArrayList<>();
		String str = "select * from machine where employe = ?";
		ResultSet rs;
		try {
			PreparedStatement ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ps.setInt(1, emp.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				machs.add(new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"),
						rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return machs;
	}

	public List<Machine> getMachinesByDates(String date1, String date2) {
		List<Machine> machs = new ArrayList<>();
		String str = "select * from machine where dateAchat between ? and ?";
		try {
			PreparedStatement ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ps.setString(1, date1);
			ps.setString(2, date2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				machs.add(new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"),
						rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return machs;
	}

	@Override
	public List<Machine> findElementsByKeyWord(String colomnName, String kw) {
		List<Machine> machs = new ArrayList<>();
		PreparedStatement ps;
		String str;
		if (colomnName.equals("reference")) {
			str = "select * from machine where reference like ?";
		} else {
			str = "select * from machine where marque like ?";
		}
		try {
			ps = Connexion.oneConnection().getConnection().prepareStatement(str);
			ps.setString(1, "%" + kw + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Machine m = new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"),
						rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe")));
				machs.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return machs;
	}
}
